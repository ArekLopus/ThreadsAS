package asyncServletCommunication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/AsyncServ4", asyncSupported=true)
public class AsyncServletWithEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Event<AsyncContext> event;
	
	@Resource
	ManagedExecutorService mes;
	
	public AsyncServletWithEvent() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		AsyncContext startAsync = request.startAsync(request,response);
		
		mes.execute(()->{
			try {
				request.setAttribute("message", "Start Message");
				startAsync.getResponse().getWriter().println("Staring Thread:<br/>"+Thread.currentThread().getName()+"<br/><br/>");
			
				event.fire(startAsync);
				Thread.sleep(3000);
		
				request.setAttribute("message", "Message1");
				event.fire(startAsync);
			
				Thread.sleep(3000);
				//if(true)throw new RuntimeException();
				request.setAttribute("message", "Message2");
			
				event.fire(startAsync);
				Thread.sleep(3000);
			
				request.setAttribute("message", "Final Message");
				event.fire(startAsync);
			} catch (InterruptedException | IOException e) {
				e.printStackTrace();
			}
		});
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
