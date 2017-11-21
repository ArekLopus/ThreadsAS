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

public class Listener2 implements ManagedTaskListener, Callable<String>, ManagedTask {
	
	@Inject
	Event<String> event;
	
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
			//event.fire(s);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void taskStarting(Future<?> future, ManagedExecutorService executor, Object task) {
		Logger.getLogger(getClass().getName()).info("-----taskStarting() called");
	}

	@Override
	public ManagedTaskListener getManagedTaskListener() {
		return this;
	}

	@Override
	public Map<String, String> getExecutionProperties() {
		return null;
	}
	
	          

	
}
