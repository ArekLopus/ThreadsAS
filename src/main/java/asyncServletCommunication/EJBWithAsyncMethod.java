package asyncServletCommunication;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class EJBWithAsyncMethod {
	
	@Asynchronous
	public void async(Runnable r) {
		r.run();
	}

}
