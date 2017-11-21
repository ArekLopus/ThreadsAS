package asyncServletCommunication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//AsyncContext - it will not run in a different thread by itself. 
//You need to use acontext.start(), @Asynchronous method of EJB, or ManagedExecutorService to run code as asynchronous

//Servlet name can be -> value/urlPatterns
@WebServlet(value = "/AsyncServ", asyncSupported=true)
public class AsyncServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AsyncContext acontext = request.startAsync(request, response);
		PrintWriter pw = response.getWriter();
		
		response.setContentType("text/html");
		
		pw.println("<h3>Hello!</h3>");
		pw.println("<br/>Thread: "+Thread.currentThread().getName());
		
		acontext.start( ()-> {
			try {
				pw.println("<BR/>Before sleep");
				pw.println("<br/>Thread: "+Thread.currentThread().getName());
				pw.flush();
				Thread.sleep(3000);
				pw.println("<BR/>After sleep");
				pw.flush();
				acontext.complete();
			} catch (Exception e) {
				System.out.println("Exception "+e.getMessage());
			}
		});
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
}
