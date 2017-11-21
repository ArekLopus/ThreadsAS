package porcupine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

import com.airhacks.porcupine.execution.boundary.Dedicated;

@Stateless
@Path("stats")
public class PorcupineResource1 {
	
	@Inject
    @Dedicated("justMyCustomName")
    ExecutorService es;
	
	@GET
	public void getAll(@Suspended AsyncResponse response) {
		CompletableFuture.supplyAsync(this::getSome, es).thenAccept(response::resume);
	}
	
	private String getSome() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Abracadabra";
	}
	
	//curl -i http://localhost:8080/ThreadsAS/res/stats
	
}
