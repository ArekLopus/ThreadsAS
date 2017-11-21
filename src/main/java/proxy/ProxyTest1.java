package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest1 {

	public ProxyTest1() {
		InvocationHandler handler2 = new MyInvocationHandlerAny();

		MyInterface2 proxy = (MyInterface2) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(), new Class[] { MyInterface2.class }, handler2);
		System.out.println(proxy.getStg());
		System.out.println(proxy.getStg2());
		System.out.println(proxy.getStg3());
	}

	public static void main(String[] args) {
		new ProxyTest1();
	}

}


class MyInvocationHandlerAny implements InvocationHandler{
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return "Method called: "+method.getName();
	}
}

