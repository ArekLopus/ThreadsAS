package executorServiceManagable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Stateless
public class ManagedExecutorServiceTest {
	
	@Inject
	ManagedExecutorServicePool mesb;
	
	public void testManExec() {
		ExecutorService es = mesb.getThreadPoolExecutor();
		
		for(int i=0; i<10; i++) {
			CompletableFuture.supplyAsync(this::getSome, es).thenAccept(this::useSome);
		}
	}
	
	private String getSome() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Abracadabra";
	}
	
	private void useSome(String s) {
		Logger.getLogger(getClass().getName()).info("Thread started: "+s);
	}
}