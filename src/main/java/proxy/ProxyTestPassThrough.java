package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

import proxy.performance.PassthroughInvocationHandlerPerformanceTest.RandomNumberGenerator;

public class ProxyTestPassThrough {
	
	private Random random = new Random();
    private RandomNumber concreteInstance = random::nextLong;
	
	public ProxyTestPassThrough() {
		InvocationHandler handler = new MyInvocationHandlerPassthrough2(concreteInstance);
		
		RandomNumber proxy = (RandomNumber) Proxy.newProxyInstance(
				RandomNumber.class.getClassLoader(), new Class[] { RandomNumber.class }, handler);
		System.out.println(proxy.getNumber());
	}
	
	
	public static void main(String[] args) {
		new ProxyTestPassThrough();
	}

}


class MyInvocationHandlerPassthrough2 implements InvocationHandler{
	
	private RandomNumber obj;
	public MyInvocationHandlerPassthrough2(RandomNumber o) {
		this.obj = o;
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(obj, args);
	}
}

interface RandomNumber {
    long getNumber();
}
	
	
//	public interface InvocationHandler {
//		  Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
//	}
	