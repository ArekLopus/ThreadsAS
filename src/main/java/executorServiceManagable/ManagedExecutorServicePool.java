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
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.ws.rs.GET;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Singleton
public class ManagedExecutorServicePool {
	
	private ExecutorService threadPoolExecutor = null;
	int  corePoolSize  =    2;
	int  maxPoolSize   =    5;
	long keepAliveTime = 3000;

	@Resource
	ManagedThreadFactory factory;

	public ExecutorService getThreadPoolExecutor() {
		return threadPoolExecutor;
	}

	@PostConstruct
	public void init() {
		Logger.getLogger(getClass().getName()).info("@PostConstruct ManagedExecutorServicePool");
		threadPoolExecutor =  new ThreadPoolExecutor(corePoolSize, maxPoolSize,
               keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), factory);      
	}
	
	@PreDestroy
	public void releaseResources() {
		Logger.getLogger(getClass().getName()).info("@PreDestroy ManagedExecutorServicePool");
		threadPoolExecutor.shutdown();
	}
	

}