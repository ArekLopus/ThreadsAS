package proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import proxy.ProxyFactory2;


public class ViewProxyImpl {
	
	public ViewProxyImpl() {
		Map identity = new HashMap();
		IPerson person = (IPerson)ViewProxy.newInstance(identity, new Class[] { IPerson.class });
		System.out.println(person.getName());
		person.setName("Bob Jones");
		System.out.println(person.getName());
		
		IEmployee employee = (IEmployee)ViewProxy.newInstance(identity, new Class[]	{ IEmployee.class });
		employee.setSSN("111-11-1111");
		System.out.println(employee.getName());
	}
	
	public static void main(String[] args) {
		new ViewProxyImpl();
	}
}


interface IPerson {
	public String getName();
	public String getAddress();
	public String getPhoneNumber();
	public void setName(String name);
	public void setAddress(String address);
	public void setPhoneNumber(String phoneNumber);
}
interface IEmployee extends IPerson {
	public String getSSN();
	public String getDepartment();
	public Float getSalary();
	public void setSSN(String ssn);
	public void setDepartment(String department);
	public void setSalary(String salary);
}
interface IManager extends IEmployee {
	public String getTitle();
	public String[] getDepartments();
	public void setTitle(String title);
	public void setDepartments(String[] departments);
}

class ViewProxy implements InvocationHandler {
	private Map map;
	public static Object newInstance(Map map, Class[] interfaces) {
		return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), interfaces, new ViewProxy(map));
	}
	public ViewProxy(Map map) {
		this.map = map;
	}
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
	    Object result;
	    String methodName = m.getName();
	    if (methodName.startsWith("get")) {
	    	String name = methodName.substring(methodName.indexOf("get")+3);
	    	return map.get(name);
	    } else if (methodName.startsWith("set")) {
	    	String name = methodName.substring(methodName.indexOf("set")+3);
	    	map.put(name, args[0]);
	    	return null;
	    } else if (methodName.startsWith("is")) {
	    	String name = methodName.substring(methodName.indexOf("is")+2);
	    	return(map.get(name));
	    }
	    return null;
	}
}