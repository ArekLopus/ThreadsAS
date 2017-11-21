package managedTaskListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listener3")

public class B3Servlet extends HttpServlet {

    @Resource(name = "DefaultManagedExecutorService")
    ManagedExecutorService executor;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter writer = response.getWriter();
      
        Future<Long> futureResult = executor.submit(new CallableTask(5));                    

        while (!futureResult.isDone()) {

               try {
                      Thread.sleep(100);
               } catch (InterruptedException e) {
                      e.printStackTrace();
               }

        }

        try {
               writer.write("Callable Task returned " +futureResult.get());
        } catch ( Exception e) {
               e.printStackTrace();
        } 
    }
    
    
    class CallableTask implements Callable<Long> {
    	private int id;

        public CallableTask(int id) {
              this.id = id;
        }

        public Long call() {
              long summation = 0;
              for (int i = 1; i <= id; i++) {
                     summation += i;
              }
              return new Long(summation);
        }
    }
}