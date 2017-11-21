package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class ProxyFactory {
	
	public static Object newProxyInstance(Object obj, Class[] interfaces) {
		return Proxy.newProxyInstance(interfaces[0].getClassLoader(), interfaces, new MyDynamicProxyHandler(obj));
	}
	
	public ProxyFactory() {
		HashMap id = new HashMap();
		ProxyFactoryIntf proxy = (ProxyFactoryIntf) ProxyFactory.newProxyInstance(id, new Class[] {ProxyFactoryIntf.class});
		
		System.out.println("Proxy: "+proxy.abc());
		System.out.println("Proxy: "+proxy.abcd());
	}
	
	public static void main(String[] args) {
		new ProxyFactory();
	}
	
	public void abcd(String... str) {
		System.out.println(str.getClass().getName());
		System.out.println(str.length);
	}
}


class MyDynamicProxyHandler implements InvocationHandler {
	Object obj;
	public MyDynamicProxyHandler(Object obj)  { 
		this.obj = obj; 
	}
	
	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		return "This is call from method: "+m.getName();
  }
}

interface ProxyFactoryIntf {
	String abc();
	String abcd();
}
