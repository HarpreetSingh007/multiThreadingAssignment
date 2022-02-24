import java.lang.reflect.Executable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Increment implements Runnable{
	int number = 0;
	
	public void run() {
		number++;	
		System.out.println(number + "  " + Thread.currentThread().getName());
	}
	
}

public class Question_2 {

	public static void main(String[] args) {
		
		
		int poolsize = Runtime.getRuntime().availableProcessors();
		
		//THIS USE 10 THREADS AND DIVIDE TASK AMOUNG THEM
		
		ExecutorService service = Executors.newFixedThreadPool(poolsize);
		
		
		//THIS CREATE AS MANY AS THREAD NEEDED BUT ONLY TAKE ONE TASK AT A TIME
		ExecutorService service1 = Executors.newCachedThreadPool(); 
		
		
		//THIS USE ONLY ONE THREAD
		ExecutorService service2 = Executors.newSingleThreadExecutor(); 
		
		
		Increment inc = new Increment();
		
		for(int i = 0 ; i < 1000 ; i++) {
			service.execute(inc);
			service1.execute(inc);
			service2.execute(inc);
		}
		
		
	}
	
}	
