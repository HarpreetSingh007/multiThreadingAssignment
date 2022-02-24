
// CREATING AND SOLVING RACE CONDITION USING SYNCRONIZED BLOCK QUESTION --3 
// CREATING THREADS USING RUNNABLE AND THREAD QUESTION --1

//AND USING SLEEP AND JOIN SLEEP
public class Question1_3 extends Thread {

	public static long num;

	Object key = new Object();

	void inc() {
		synchronized (key) {
			num = num + 1;
		}

	}

	@Override
	public void run() {

		for (int i = 0; i < 10; i++) {
			System.out.println("printing Done By Thread : " + Thread.currentThread().getName());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws InterruptedException {

		// this code show sleep method working

		Thread testSleep = new Question1_3();
		testSleep.start();
		testSleep.join();

		// end of sleep working

		// this block of code show runnable interface implementation and Show
		// Syncronization between 1000 threads
		// using syncronization block

		Thread[] t1 = new Thread[1000];
		Question1_3 m = new Question1_3();

		Runnable r = () -> {

			for (int i = 0; i < 1000; i++) {
				m.inc();
			}

		};

		for (int i = 0; i < t1.length; i++) {

			t1[i] = new Thread(r);
			t1[i].start();
		}
		for (int i = 0; i < t1.length; i++) {
			t1[i].join();
		}

		System.out.println(num);

	}

}
