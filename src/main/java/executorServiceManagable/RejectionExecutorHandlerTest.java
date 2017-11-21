package executorServiceManagable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectionExecutorHandlerTest {

	public RejectionExecutorHandlerTest() throws InterruptedException {
		
		RejectedExecutionHandler reh = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("rejected");
            }
        };
		
        RejectedExecutionHandler rehLambda = (runnable, executor) -> {
            System.out.println("rejected (lambda)");
        };
        
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1), Executors.defaultThreadFactory(), reh);
		tpe.setRejectedExecutionHandler(rehLambda);
		
		Runnable runnable = () -> {
            System.out.println("work  started");
            try {
            	Thread.sleep(1000);
            } catch (InterruptedException e) {
            	e.printStackTrace();
            }
            System.out.println("work  finished");
        };
        
        tpe.execute(runnable);
        tpe.execute(runnable);
        tpe.execute(runnable);
        
        Thread.sleep(3000);
        System.out.println();
        System.out.println("ActiveCount "+tpe.getActiveCount());
        System.out.println("CompletedTaskCount "+tpe.getCompletedTaskCount());
        System.out.println("CorePoolSize "+tpe.getCorePoolSize());
        System.out.println("KeepAliveTime "+tpe.getKeepAliveTime(TimeUnit.MILLISECONDS));
        System.out.println("LargestPoolSize "+tpe.getLargestPoolSize());
        System.out.println("MaximumPoolSize "+tpe.getMaximumPoolSize());
        System.out.println("PoolSize "+tpe.getPoolSize());
        System.out.println("TaskCount "+tpe.getTaskCount());
        
        
        tpe.shutdown();
	}

	public static void main(String[] args) throws InterruptedException {
		new RejectionExecutorHandlerTest();
	}

}
