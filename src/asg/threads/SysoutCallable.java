package asg.threads;

import java.util.concurrent.Callable;

public class SysoutCallable implements Callable<String>, ThreadLocalHandler<TenantIdentifier> {
	
	private TenantIdentifier tid;
	
	public SysoutCallable(TenantIdentifier tid) {
		this.tid = tid;
	}
	
	@Override
	public String call() throws Exception {	
		return "Returning from callable" + Thread.currentThread().getName() + " Thread local " + ThreadLocalHolder.get() + " tid " + tid;
	}

	@Override
	public void beforeExecute() {
		ThreadLocalHolder.set(tid);		
	}

	@Override
	public void afterExecute() {
		ThreadLocalHolder.set(null);
		
	}

	@Override
	public TenantIdentifier getValueToSet() {
		return this.tid;
		
	}

}
