package asg.threads;

import java.util.List;
import java.util.Vector;

public class VectorThread {
	
	public static void main(String[] args) {
		List<Long> nums = new Vector<>();
		nums.add(1L);
		nums.add(2L);
		nums.add(3L);
		List<Long> nums1 = new Vector<>();
		
		
		Thread t1 = new Thread(() -> {
			Long id = nums.get(0);
			System.out.println(Thread.currentThread().getName() + " : size : "
		+ nums.size() + " 1st : " + id);
			if(!nums1.contains(id)) {
				nums1.add(id);
			} else {
				synchronized(id) {
				try {
					id.wait(); 
				} catch (InterruptedException e) {
					System.out.println(e);
				}	
				}
			}
			synchronized(id) {
				id.notifyAll();
				nums1.remove(0); 
			}
//			System.out.println(Thread.currentThread().getName() + " : size after remove : "
//					+ nums.size() + " 1st : " + nums.get(0));
			
		}, "Thread 1");
		
		Thread t2 = new Thread(() -> {
			Long id = nums.get(0);
			System.out.println(Thread.currentThread().getName() + " : size : "
		+ nums.size() + " 1st : " + id);
			if(!nums1.contains(id)) {
				nums1.add(id);
			} else {
				synchronized(id) {
				try {
					id.wait();
				} catch (InterruptedException e) {
					System.out.println(e);
				}	
				}
			}
			synchronized(id) {
				id.notifyAll();
				nums1.remove(0); 
			}
//			System.out.println(Thread.currentThread().getName() + " : size after remove : "
//					+ nums.size() + " 1st : " + nums.get(0));
			
		}, "Thread 2");
		
		t1.start();
		t2.start();
		
	}

}
