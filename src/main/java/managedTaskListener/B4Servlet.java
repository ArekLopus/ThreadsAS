package managedTaskListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedExecutors;
import javax.enterprise.concurrent.ManagedTaskListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/listener4")

public class B4Servlet extends HttpServlet {

    @Resource
    ManagedExecutorService executor;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        
        Logger.getLogger(getClass().getName()).info("Lets get started! (true/false - isDone())\n");
        CallableListenerTask lis = new CallableListenerTask(6);
        Future<Long> futureResult = executor.submit(ManagedExecutors.managedTask(lis, lis));     
        
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
    
    
    class CallableListenerTask implements Callable<Long>,ManagedTaskListener {

        private int id;

        public CallableListenerTask(int id) {
              this.id = id;
        }

        public Long call() {
              long summation = 0;

              for (int i = 1; i <= id; i++) {
                     summation += i;
              }
              return new Long(summation);
        }

        public void taskSubmitted(Future<?> f, ManagedExecutorService es, Object obj) {
        	Logger.getLogger(getClass().getName()).info("Task Submitted! "+f.isDone());
        }

        public void taskDone(Future<?> f, ManagedExecutorService es, Object obj, Throwable exc) {
        	Logger.getLogger(getClass().getName()).info("Task DONE! "+f.isDone());
        }

        public void taskStarting(Future<?> f, ManagedExecutorService es, Object obj) {
        	Logger.getLogger(getClass().getName()).info("Task Starting! "+f.isDone());
        }

        public void taskAborted(Future<?> f, ManagedExecutorService es, Object obj, Throwable exc) {
        	Logger.getLogger(getClass().getName()).info("Task Aborted! "+f.isDone());
        }

 } 
}