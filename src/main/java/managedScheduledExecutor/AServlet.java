package managedScheduledExecutor;

import java.io.IOException;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

@WebServlet(urlPatterns="/schedule", asyncSupported=true)
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AServlet() {
        super();
    }
    
    @EJB
    ScheduledTest st;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>ManagedScheduledExecutorService test</h2>");
		response.getWriter().println("<br/>");
		response.getWriter().write("Waiting 5 seconds before firing the task");
		st.scheduledOneRun();
		//st.scheduledFixedRate();
		st.scheduledWithReturn();
		response.getWriter().write("");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
