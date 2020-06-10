package asg.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ExecutorServiceTester {

	private static ThreadPoolExecutor tpe = new ThreadPoolExecutor(3, 5, 5L, TimeUnit.MINUTES,
			new LinkedBlockingQueue<>()) {
		protected void beforeExecute(Thread t, Runnable r) {
			if(r instanceof ThreadLocalHandler) {
				((ThreadLocalHandler)r).beforeExecute();
			}
		}

		protected void afterExecute(Runnable r, Throwable t) {
			if(r instanceof ThreadLocalHandler) {
				((ThreadLocalHandler)r).afterExecute();
			}
		}
		
		protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		        return new MultitenantAwareFutureTask<T>(callable);
		}
		
		
	};

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		java.util.Random random = new Random();
		List<Future> results = new ArrayList<> ();
		IntStream.range(1, 100).forEach(i -> {
			tpe.execute(new SysoutRunnable(random.nextBoolean() ? TenantIdentifier.ONE : TenantIdentifier.TWO));
			results.add(tpe.submit(new SysoutCallable(random.nextBoolean() ? TenantIdentifier.TWO : TenantIdentifier.ONE)));
		});
		
		
		
		results.stream().forEach(f -> {
			try {
				System.out.println(f.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		System.out.println(ThreadLocalHolder.get());
	}

}
