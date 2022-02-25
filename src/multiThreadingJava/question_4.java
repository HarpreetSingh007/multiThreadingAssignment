package multiThreadingJava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;




public class question_4 {
		
	
	
	
	public static void main(String arg[]) throws InterruptedException, ExecutionException {
		
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<Integer> future = service.submit(new Task());
			
			System.out.println(future.get());

	}
	
	public static class Task implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			Thread.sleep(100);
			return 2;
		}
		
	}
	
}
