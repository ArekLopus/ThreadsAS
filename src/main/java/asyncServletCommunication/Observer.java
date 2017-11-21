package asyncServletCommunication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.servlet.AsyncContext;
import javax.transaction.Transactional;

public class Observer {
	
	public void getMessSucess(@Observes final AsyncContext ac) throws IOException {
		String message = (String) ac.getRequest().getAttribute("message");
		System.out.println(message);
		
		ac.start(()->{
			try {
				PrintWriter pw = ac.getResponse().getWriter();
				pw.println("From Async servlet: "+ac.getRequest().getAttribute("message")+",<br/>Thread: "+Thread.currentThread().getName()+"<br/><br/>");
				pw.flush();
				if(message.equals("Final Message")) {
					ac.complete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
	}
	
//	@Transactional
//	public void getMessSucess(@Observes(during=TransactionPhase.AFTER_SUCCESS) AsyncContext ac) throws IOException {
//		System.out.println(ac.getRequest().getAttribute("message"));
//		PrintWriter pw = ac.getResponse().getWriter();
//		pw.println("From Async servlet: "+ac.getRequest().getAttribute("message")+"<br/>");
//		pw.flush();
//		ac.complete();		//causes the thread to wake up and pushes the content to the server
//	}
//	
//	//TransactionPhase!
//	@Transactional
//	public void getMessFAil(@Observes(during=TransactionPhase.AFTER_FAILURE) AsyncContext ac) throws IOException {
//		System.out.println("Got AsyncContext (Failre): ");
//		PrintWriter pw = ac.getResponse().getWriter();
//		pw.println("From Async servlet: AsyncContext Failure"+"<br/>");
//		pw.flush();
//		ac.complete();
//	}
	
//	public void getString(@Observes(during=TransactionPhase.AFTER_SUCCESS) String s) throws IOException {
//		System.out.println("Got String (Sucess): "+s);
//		
//	}
//	public void getStringFail(@Observes(during=TransactionPhase.AFTER_FAILURE) String s) throws IOException {
//		System.out.println("Got String (Failre): "+s);
//	}
	
}
