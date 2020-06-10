package asg.threads;

import java.util.stream.IntStream;

public class HelloThreads {

	public static void main(String[] args) {
		Thread thread1 = new Thread();
		thread1.start();
		IntStream.rangeClosed(1, 10).sequential().forEach(i -> {
			if (i == 5) {
				try {
					thread1.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("I = " + i);
		});
	}

}
