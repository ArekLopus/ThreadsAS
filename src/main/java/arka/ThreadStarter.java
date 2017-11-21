package arka;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;

@Stateless
public class ThreadStarter {

	@Resource
	ManagedExecutorService mes;
	
	public void executorAsync() {
		this.mes.submit(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					Logger.getLogger("ThreadStarter.class").info("Thread is awaken! "+Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
	}

}
