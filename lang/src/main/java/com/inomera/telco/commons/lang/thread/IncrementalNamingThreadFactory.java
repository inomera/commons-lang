package com.inomera.telco.commons.lang.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadGroup API Note:
 * Thread groups provided a way in early Java releases to group threads and provide a form of job control for threads.
 * Thread groups supported the isolation of applets and defined methods intended for diagnostic purposes.
 * It should be rare for new applications to create ThreadGroups and interact with this API.
 * use {@link IncrementalNamingVirtualThreadFactory}
 */
public class IncrementalNamingThreadFactory implements ThreadFactory, Thread.UncaughtExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(IncrementalNamingThreadFactory.class);

	private String threadNamePrefix;
	private int threadPriority = Thread.NORM_PRIORITY;
	private boolean daemon = false;
	private ThreadGroup threadGroup;
	private final AtomicInteger threadCount = new AtomicInteger(0);

	public IncrementalNamingThreadFactory(String threadNamePrefix) {
		this.threadNamePrefix = (threadNamePrefix != null ? threadNamePrefix : getDefaultThreadNamePrefix());
	}

	public Thread newThread(Runnable runnable) {
		return createThread(runnable);
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		LOG.error("Unexpected error '{}' in thread '{}'!.", e.getMessage(), t.getName(), e);
	}

	/**
	 * Specify the prefix to use for the names of newly created threads.
	 * Default is "SimpleAsyncTaskExecutor-".
	 */
	public void setThreadNamePrefix(String threadNamePrefix) {
		this.threadNamePrefix = (threadNamePrefix != null ? threadNamePrefix : getDefaultThreadNamePrefix());
	}

	/**
	 * Return the thread name prefix to use for the names of newly
	 * created threads.
	 */
	public String getThreadNamePrefix() {
		return this.threadNamePrefix;
	}

	/**
	 * Set the priority of the threads that this factory creates.
	 * Default is 5.
	 *
	 * @see java.lang.Thread#NORM_PRIORITY
	 */
	public void setThreadPriority(int threadPriority) {
		this.threadPriority = threadPriority;
	}

	/**
	 * Return the priority of the threads that this factory creates.
	 */
	public int getThreadPriority() {
		return this.threadPriority;
	}

	/**
	 * Set whether this factory is supposed to create daemon threads,
	 * just executing as long as the application itself is running.
	 * <p>Default is "false": Concrete factories usually support explicit cancelling.
	 * Hence, if the application shuts down, Runnables will by default finish their
	 * execution.
	 * <p>Specify "true" for eager shutdown of threads which still actively execute
	 * a {@link Runnable} at the time that the application itself shuts down.
	 *
	 * @see java.lang.Thread#setDaemon
	 */
	public void setDaemon(boolean daemon) {
		this.daemon = daemon;
	}

	/**
	 * Return whether this factory should create daemon threads.
	 */
	public boolean isDaemon() {
		return this.daemon;
	}

	/**
	 * Specify the name of the thread group that threads should be created in.
	 *
	 * @see #setThreadGroup
	 */
	public void setThreadGroupName(String name) {
		this.threadGroup = new ThreadGroup(name);
	}

	/**
	 * Specify the thread group that threads should be created in.
	 *
	 * @see #setThreadGroupName
	 */
	public void setThreadGroup(ThreadGroup threadGroup) {
		this.threadGroup = threadGroup;
	}

	/**
	 * Return the thread group that threads should be created in
	 * (or {@code null} for the default group).
	 */
	public ThreadGroup getThreadGroup() {
		return this.threadGroup;
	}

	/**
	 * Template method for the creation of a new {@link Thread}.
	 * <p>The default implementation creates a new Thread for the given
	 * {@link Runnable}, applying an appropriate thread name.
	 *
	 * @param runnable the Runnable to execute
	 * @see #nextThreadName()
	 */
	public Thread createThread(Runnable runnable) {
		Thread thread = new Thread(getThreadGroup(), runnable, nextThreadName());
		thread.setPriority(getThreadPriority());
		thread.setDaemon(isDaemon());
		thread.setUncaughtExceptionHandler(this);
		return thread;
	}

	/**
	 * Return the thread name to use for a newly created {@link Thread}.
	 * <p>The default implementation returns the specified thread name prefix
	 * with an increasing thread count appended: e.g. "SimpleAsyncTaskExecutor-0".
	 *
	 * @see #getThreadNamePrefix()
	 */
	protected String nextThreadName() {
		return getThreadNamePrefix() + this.threadCount.incrementAndGet();
	}

	/**
	 * Build the default thread name prefix for this factory.
	 *
	 * @return the default thread name prefix (never {@code null})
	 */
	protected String getDefaultThreadNamePrefix() {
		return getClass().getSimpleName() + "-";
	}
}
