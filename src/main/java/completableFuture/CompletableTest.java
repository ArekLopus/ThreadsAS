package completableFuture;

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
public class CompletableTest {
	
	@Resource
    ManagedExecutorService mes;
	
	public void completableTest() {
		Supplier<String> supplier = this::supp; 
		Consumer<String> consumer = this::cons;
		
		CompletableFuture.supplyAsync(supplier, mes).thenAccept(consumer);
		CompletableFuture.supplyAsync(supplier).thenAcceptAsync(consumer);
		CompletaBleNow(supplier, consumer);
	}
	
	
	public String supp() {
		return "Test";
	}
	
	public void cons(String str) {
		Logger.getLogger(getClass().getName()).info("Consumed: "+str);
	}
	
	public void CompletaBleNow(Supplier<String> supplier, Consumer<String> consumer) {
		consumer.accept(supplier.get());
	}
}
