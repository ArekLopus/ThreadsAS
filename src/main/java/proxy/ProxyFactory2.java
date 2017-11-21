package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ProxyFactory2 {
	
	public ProxyFactory2() {
		TestInterface ti = ProxyFactory2.getProxy(new ArrayList<Object>(), TestInterface.class);
		ti.doIt();
	}
	
	
	public static <T> T getProxy(Object passThrough, Class<? extends T> intf) {
		return simpleProxy(intf, new Handler(passThrough));
	}

	public static <T> T simpleProxy(Class<? extends T> iface, InvocationHandler handler, Class<?>...otherIfaces) {
		Class<?>[] allInterfaces = Stream.concat(Stream.of(iface), Stream.of(otherIfaces)).distinct().toArray(Class<?>[]::new);
		return (T) Proxy.newProxyInstance(iface.getClassLoader(), allInterfaces, handler);
	}
	
	public static void main(String[] args) {
		new ProxyFactory2();
	}

}


interface TestInterface {
	void doIt();
}

class Handler implements InvocationHandler {
	
	private Object o;
	
	public Handler() {}
	public Handler(Object t) {
		this.o = t;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(o != null) {
			System.out.println("Passed object type: "+o.getClass().getName());
		} else {
			System.out.println("No Passed object");
		}
		System.out.println("Bla");
		return null;
	}
}

