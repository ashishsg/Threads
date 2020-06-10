package asg.threads;

public class ThreadLocalHolder {
	
	private static ThreadLocal<TenantIdentifier> threadLocal = new ThreadLocal<>();
	
	public static TenantIdentifier get() {
		return threadLocal.get();
	}
	
	public static void set(TenantIdentifier s) {
		threadLocal.set(s);
	}
}
