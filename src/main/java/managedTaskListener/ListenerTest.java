package managedTaskListener;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedExecutors;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;
import javax.enterprise.event.Observes;

@Stateless
public class ListenerTest {
	
	@Resource
    ManagedExecutorService mes;
	
	public void listenerTest() {
		//Opcja 1 - implements ManagedTaskListener, Callable<String> i ManagedExecutors.managedTask
		Listener lis = new Listener();
		Future<String> futureResult = mes.submit(ManagedExecutors.managedTask(lis, lis));
		
		//Opcja 2 - implements ManagedTaskListener, Callable<String>, ManagedTask
		//Future<String> futureResult = mes.submit(new Listener2());
		
	}
	
}
