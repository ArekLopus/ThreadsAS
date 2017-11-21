package completableFutureWithEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Stateless
@Path("answers2")
public class AnswersResorce2 {
	
	@Resource
	ManagedExecutorService mes;
	
	@Inject
	Thinker thinker;
	
	@GET
	public void getAnswer1(@Suspended AsyncResponse response) {
		Consumer<Object> consumer = response::resume;
		Supplier<String> answerSupplier = thinker::theAnswer;
		try {
			CompletableFuture.supplyAsync(answerSupplier, mes).thenAccept(consumer).get();
		} catch (InterruptedException | ExecutionException e) {
			Logger.getLogger(this.getClass().getName()).info(e.getMessage());
		}		
		Logger.getLogger(this.getClass().getName()).info("getAnswer1() after called");
	}
	
	
}
