package asg.threads;

public class SysoutRunnable implements Runnable, ThreadLocalHandler<TenantIdentifier> {
	private TenantIdentifier tid;
	
	public SysoutRunnable(TenantIdentifier tid) {
		this.tid = tid;
	}
	@Override
	public void run() {
		System.out.println("Printing from runnable - " + Thread.currentThread().getName() + " Thread local -" + ThreadLocalHolder.get() + " tid " + tid);
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
