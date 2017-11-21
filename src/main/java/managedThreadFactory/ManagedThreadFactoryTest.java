package managedThreadFactory;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedThreadFactory;

@Stateless
public class ManagedThreadFactoryTest {

	@Resource
    ManagedThreadFactory factory;
	
	public void mangedThread() {
		
		Thread thread = factory.newThread(new SimpleTask());
		thread.setName("MyManagedThread");
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
		
	}
	
	
	class SimpleTask implements Runnable {
		
		@Override
		public void run() {
			Logger.getLogger(getClass().getName()).info("Thread started: ");
		}
	}
	
}
