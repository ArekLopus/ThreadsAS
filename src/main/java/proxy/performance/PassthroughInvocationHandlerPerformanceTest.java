package proxy.performance;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;
import java.util.stream.Stream;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class PassthroughInvocationHandlerPerformanceTest {
	
	//Trwa około 1 min
	
    @Rule
    public final ContiPerfRule rule = new ContiPerfRule();

    public interface RandomNumberGenerator {
        long getNumber();
    }

    private static final Random random = new Random();
    private static final RandomNumberGenerator concreteInstance = random::nextLong;
    private static final RandomNumberGenerator proxiedInstance = passthroughProxy(
        RandomNumberGenerator.class, concreteInstance);

    @Test
    @PerfTest(invocations = 1000, warmUp = 200)
    public void invokeConcrete() {
        getAMillionRandomLongs(concreteInstance);
    }

    @Test
    @PerfTest(invocations = 1000, warmUp = 200)
    public void invokeProxied() {
        getAMillionRandomLongs(proxiedInstance);
    }

    private void getAMillionRandomLongs(RandomNumberGenerator generator) {
        for (int i = 0; i < 1000000; i++) {
            generator.getNumber();
        }
    }
    
    public static <T> T passthroughProxy(Class<? extends T> iface, T target) {
        return simpleProxy(iface, new PassthroughInvocationHandler(target));
    }
    public static <T> T simpleProxy(Class<? extends T> iface, InvocationHandler handler, Class<?>...otherIfaces) {
        Class<?>[] allInterfaces = Stream.concat(Stream.of(iface), Stream.of(otherIfaces)).distinct().toArray(Class<?>[]::new);
        return (T) Proxy.newProxyInstance(iface.getClassLoader(), allInterfaces, handler);
    }
}

class PassthroughInvocationHandler implements InvocationHandler {
    private final Object target;

    public PassthroughInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }
}

