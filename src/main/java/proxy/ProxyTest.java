package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public ProxyTest() {
		InvocationHandler handler = new MyInvocationHandler();

		MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
                  MyInterface.class.getClassLoader(), new Class[] { MyInterface.class }, handler);
		System.out.println(proxy.getStg());
		System.out.println(proxy.getStg("stg"));
		
	}

	public static void main(String[] args) {
		new ProxyTest();
	}

}


class MyInvocationHandler implements InvocationHandler{
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String pr = "null";
		String me = "null";
		String ar = "null";
		if(proxy != null) {
			pr = proxy.getClass().getName();
		}
		if(method != null) {
			me = method.getName();
		}
		if(args != null) {
			ar = String.valueOf(args.length);
		}
		return ("proxy: "+pr+", method: "+me+", args: "+ar);
	}
}
	
//	public interface InvocationHandler {
//		  Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
//	}
	