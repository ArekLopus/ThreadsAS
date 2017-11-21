package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest2 {

	public ProxyTest2() {
		InvocationHandler handler2 = new MyInvocationHandler2();

		MyInterface2 proxy = (MyInterface2) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(), new Class[] { MyInterface2.class }, handler2);
		System.out.println(proxy.getStg());
		System.out.println(proxy.getStg2());
		System.out.println(proxy.getStg3());
	}

	public static void main(String[] args) {
		new ProxyTest2();
	}

}


class MyInvocationHandler2 implements InvocationHandler{
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().equals("getStg"))
			return ("getStg");
		else if (method.getName().equals("getStg2"))
			return ("getStg2");
		return "Unknown method";
	}
}

	
	
//	public interface InvocationHandler {
//		  Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
//	}
	