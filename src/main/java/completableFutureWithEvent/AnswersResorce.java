package completableFutureWithEvent;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Stateless
@Path("answers")
public class AnswersResorce {
	
	@Inject
	Event<Consumer<Object>> event;
	
	//Nie działa na Wildfly???? Na GF ruszyło
	@GET
	public void getAnswer1(@Suspended AsyncResponse response) {
		Consumer<Object> consumer = response::resume;
		event.fire(consumer);
		Logger.getLogger(this.getClass().getName()).info("getAnswer1 after fire called");
	}
	
	@GET
	@Path("a")
	public void getAnswer2(@Suspended AsyncResponse response) {
		response.resume("bla");
	}
	
	
	@GET
	@Path("b")
	public String getAnswer3() {
		return "test";
	}
}
