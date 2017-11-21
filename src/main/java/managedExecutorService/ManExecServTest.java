package managedExecutorService;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;


@Stateless
public class ManExecServTest {
	
	@Resource
    ManagedExecutorService mes;
	
	public void testManExecutor() {
		Future<Long> futureResult = mes.submit(new CallableTask(5));
		
		try {
			Logger.getLogger(getClass().getName()).info("Result: "+futureResult.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
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
