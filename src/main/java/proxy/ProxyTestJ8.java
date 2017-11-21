package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTestJ8 {

	public ProxyTestJ8() {
		MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
                  MyInterface.class.getClassLoader(), new Class[] { MyInterface.class },
                  (pr, me, args) -> {
                	  if(args != null)
                		  return ("getStg(WithArgs) called");
                	  else 
                		  return ("getStg(NoArgs) called");
                  });
		System.out.println(proxy.getStg());
		System.out.println(proxy.getStg("stg"));
		
	}

	public static void main(String[] args) {
		new ProxyTestJ8();
	}

}
