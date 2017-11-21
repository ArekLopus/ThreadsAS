package managedExecutorService;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/manexec", asyncSupported=true)
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	ManExecServTest mest;
	
    public AServlet() {
        super();
    }
    
    @EJB
    ManExecServTest lt;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>ManagedExecutorService tests</h2>");
		response.getWriter().println("<br/>");
		mest.testManExecutor();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
