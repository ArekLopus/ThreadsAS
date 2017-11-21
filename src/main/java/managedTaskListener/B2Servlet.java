package managedTaskListener;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listener2")

public class B2Servlet extends HttpServlet {

    @Resource(name = "DefaultManagedExecutorService")

    ManagedExecutorService executor;

       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  

             PrintWriter writer = response.getWriter();           
             executor.execute(new SimpleTask());          
             writer.write("Task SimpleTask executed! check logs");      

       }

       
   class SimpleTask implements Runnable {

	   @Override
	   public void run() {
		   System.out.println("Thread started.");
	   }
	   
    }
       
}