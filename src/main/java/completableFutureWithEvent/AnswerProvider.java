package completableFutureWithEvent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Stateless
public class AnswerProvider {
	
	@Resource
	ManagedExecutorService mes;
	
//	@Inject
//	Thinker thinker;
	
	public void onAnswer(@Observes Consumer<Object> consumer) {
		Logger.getLogger(this.getClass().getName()).info("Consumer called "+consumer.toString());
//		Supplier<String> answerSupplier = thinker::theAnswer;
		Supplier<String> answerSupplier = this::theAnswer;
		CompletableFuture.supplyAsync(answerSupplier, mes).thenAccept(consumer);
//		try {
//			CompletableFuture.supplyAsync(answerSupplier, mes).thenAccept(consumer).get();
//			Logger.getLogger(this.getClass().getName()).info("---- onAnswer(@Observes Consumer<Object> consumer) called "+consumer.toString());
//		} catch (InterruptedException | ExecutionException e) {
//			Logger.getLogger(this.getClass().getName()).info(e.getMessage());
//		}
	}
	
	public String theAnswer() {
		return "This is the only true answer ;)";
	}
	

}
