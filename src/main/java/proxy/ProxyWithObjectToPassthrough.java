package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyWithObjectToPassthrough {

	public ProxyWithObjectToPassthrough() {
		
		List<String> list = new ArrayList<>();
		list.add("abc");

		@SuppressWarnings("unchecked")
		List<String> proxy = (List<String>) Proxy.newProxyInstance(
			MyInvocationHandlerWith.class.getClassLoader(), new Class[] { List.class }, new MyInvocationHandlerWith(list));
		
		System.out.println("Added?: "+proxy.add("def"));
		System.out.println(proxy.size());
		System.out.println(proxy.get(0));
	}

	public static void main(String[] args) {
		new ProxyWithObjectToPassthrough();
	}

}


class MyInvocationHandlerWith implements InvocationHandler{
	
	private List<String> list;

	public MyInvocationHandlerWith(List<String> list) {
		this.list=list;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().startsWith("add"))
			return false;
		else return method.invoke(list, args);
	}
}

