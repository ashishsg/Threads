package asg.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MultitenantAwareFutureTask<V> extends FutureTask<V> implements ThreadLocalHandler<TenantIdentifier> {
	private TenantIdentifier tid;
	public MultitenantAwareFutureTask(Callable<V> callable) {
		super(callable);
		if(callable instanceof ThreadLocalHandler) {
			this.tid = (TenantIdentifier) ((ThreadLocalHandler)callable).getValueToSet();
		}
	}
	
	@Override
	public TenantIdentifier getValueToSet() {
		return this.tid;
	}

	@Override
	public void beforeExecute() {
		ThreadLocalHolder.set(tid);		
	}

	@Override
	public void afterExecute() {
		ThreadLocalHolder.set(null);
		
	}

}
