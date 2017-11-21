package completableFutureJ8MethRef;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TestMethodReferencePass {
	
	private List<String> list = new ArrayList<>();
	private Executor ex = Executors.newCachedThreadPool();
	
	
	public TestMethodReferencePass() {
		list.add("arek");
		justCheck(list::add);	//Przekazujemy referencję do metody
	}
	
	public void justCheck(Consumer<String> consumer) {
		ex.execute( () -> doItAsync(consumer));
		System.out.println("Size: "+list.size()+", Thread: "+Thread.currentThread().getName());
	}
	
	
	public void doItAsync(Consumer<String> consumer) {
		Supplier<String> supplier = this::addToList;
		CompletableFuture.supplyAsync(supplier, ex).thenAccept(consumer);	//Tu wykorzystujemy tą referencję
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Size: "+list.size()+", Thread: "+Thread.currentThread().getName());
	}
	
	private String addToList() {
		System.out.println("Size: "+list.size()+", Thread: "+Thread.currentThread().getName());
		return "darek";
	}
	
	
	
	
	public static void main(String[] args) {
		new TestMethodReferencePass();
	}

}
