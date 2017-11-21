package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyAuditExample implements InvocationHandler, InventoryService {

    private Object obj;

    private ProxyAuditExample(Object obj) {
        this.obj = obj;
    }
    public ProxyAuditExample() {
    	InventoryService is = (InventoryService) ProxyAuditExample.newInstance(this);
    	is.service1();
    	is.service2();
    }
    
    public static Object newInstance(Object obj) {
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
        		obj.getClass().getInterfaces(), new ProxyAuditExample(obj));
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        Object result;
        try {
        	System.out.println("----before method " + m.getName());
            long start = System.nanoTime();
            result = m.invoke(obj, args);
            long end = System.nanoTime();
            System.out.println(String.format("%s took %d ms", m.getName(), (end-start)/(1000*1000)) );
        } catch (InvocationTargetException e) {
            throw e.getTargetException(); 
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        } finally {
        	System.out.println("after method " + m.getName());
        }
        return result;
    }
    
    public static void main(String[] args) {
		new ProxyAuditExample();
	}
    

    @Override
	public void service1() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void service2() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}


interface InventoryService {
    void service1();
    void service2();
}