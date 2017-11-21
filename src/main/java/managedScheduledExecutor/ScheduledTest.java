package managedScheduledExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;

@Stateful
public class ScheduledTest {

	@Resource
	ManagedScheduledExecutorService mses;
	
	public void scheduledOneRun() {
		ScheduledFuture<?> futureResult = mses.schedule(new SimpleTask(1), 5, TimeUnit.SECONDS);
	}
	
	public void scheduledFixedRate() {
		ScheduledFuture<?> futureResult = mses.scheduleAtFixedRate (new SimpleTask(2),1, 10, TimeUnit.SECONDS);
	}
	
	public void scheduledWithReturn() {
		ScheduledFuture<Long> futureResult = mses.schedule(new CallableTask(5), 5, TimeUnit.SECONDS);                 
	
		while (!futureResult.isDone()) {       
			try {
				Thread.sleep(100); // Wait
			} catch (InterruptedException e) {                   
				e.printStackTrace();
			}
		}

		try {
			Logger.getLogger(getClass().getName()).info("Callable Task after 5s returned " +futureResult.get());
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
	
	
	class SimpleTask implements Runnable {
		
		private int i=0;

		public SimpleTask() {}
		public SimpleTask(int i) {
			this.i = i;
		}
		
		@Override
		public void run() {
			Logger.getLogger(getClass().getName()).info("Thread started: "+i);
		}
	}
	
	
	class CallableTask implements Callable<Long> {
    	private int id;

        public CallableTask(int id) {
              this.id = id;
        }

        public Long call() {
              long summation = 0;
              for (int i = 1; i <= id; i++) {
                     summation += i;
              }
              return new Long(summation);
        }
    }
}
