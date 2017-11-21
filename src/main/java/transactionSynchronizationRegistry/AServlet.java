package transactionSynchronizationRegistry;

import java.io.IOException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

@WebServlet("/tsr")
public class AServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Inject
	Runner r;
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
    public AServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter().println("<h2>TransactionSynchronizationRegistry tests</h2>");
		response.getWriter().println(r.runIt());
		response.getWriter().println("<br/>Transaction status in servlet: "+tsr.getTransactionStatus());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
