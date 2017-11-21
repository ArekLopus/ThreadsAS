package managedTaskListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class Listener implements ManagedTaskListener, Callable<String> {
	
	@Override
	public String call() throws Exception {
		try {
			Logger.getLogger(getClass().getName()).info("call() called");
			Thread.sleep(2000);
			return "Done hard work";
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "Error";
		}
		
	}
	
	@Override
	public void taskSubmitted(Future<?> future,	ManagedExecutorService executor, Object task) {
		Logger.getLogger(getClass().getName()).info("-----taskSubmitted() called");
	}

	@Override
	public void taskAborted(Future<?> future, ManagedExecutorService executor, Object task, Throwable exception) {
		Logger.getLogger(getClass().getName()).info("-----taskAborted() called");
	}

	@Override
	public void taskDone(Future<?> future, ManagedExecutorService executor, Object task, Throwable exception) {
		//Logger.getLogger(getClass().getName()).info("-----taskDone() called: ");
		try {
			String s = (String) future.get();
			Logger.getLogger(getClass().getName()).info("-----taskDone() called: "+s);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void taskStarting(Future<?> future, ManagedExecutorService executor, Object task) {
		Logger.getLogger(getClass().getName()).info("-----taskStarting() called");
	}



	
	
	          

	
}
