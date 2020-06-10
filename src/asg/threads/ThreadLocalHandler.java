package asg.threads;

public interface ThreadLocalHandler<T> {
	void beforeExecute();
	void afterExecute();
	T getValueToSet();

}
