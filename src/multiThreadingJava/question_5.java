package multiThreadingJava;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class question_5 {

	private static int[] buffer;
	private static int count;
	
	static Lock lock = new ReentrantLock(true);
	
	static class producer{
		
		void produce() throws InterruptedException {
			
			synchronized (lock) {
				while(isFull(buffer)) {
					
					lock.wait();
				}
				buffer[count++] = 1;
				lock.notify();
			}
			
		}
		
	}
	static class consumer{
		void consume() throws InterruptedException {
			
			synchronized (lock) {
				while(isEmpty(buffer)) {
					lock.wait();
					}
				
				buffer[--count] = 0;
				lock.notify();
				}
			}
			
	}
	
	static boolean isFull(int buffer[]) {
		return count ==  buffer.length;
	}
	static boolean isEmpty(int buffer[]) {
		return count == 0;
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		buffer = new int[10];
		count = 0;
		
		producer producer = new producer();
		consumer consumer = new consumer();
		
		Runnable consumerTaks = () ->{
			for(int i = 0 ; i < 10; i++) {
				try {
					consumer.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		Runnable producerTask = () ->{
			
			for(int i = 0 ; i < 15 ; i++) {
				try {
					producer.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		
		Thread c = new Thread(consumerTaks);
		Thread p = new Thread(producerTask);
		
		c.start();
		p.start();
		
		c.join();
		p.join();
		
		System.out.println(count);
		
		
	}
}
