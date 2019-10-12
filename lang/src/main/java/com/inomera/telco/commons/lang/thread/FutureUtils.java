package com.inomera.telco.commons.lang.thread;

import com.inomera.telco.commons.lang.fault.UncheckedExecutionException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class FutureUtils {

	public static <V> V getUnchecked(Future<V> future) {
		try {
			return getUninterruptibly(future);
		} catch (ExecutionException e) {
			wrapAndThrowUnchecked(e.getCause());
			throw new AssertionError();
		}
	}

	public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
		boolean interrupted = false;
		try {
			while (true) {
				try {
					return future.get();
				} catch (InterruptedException e) {
					interrupted = true;
				}
			}
		} finally {
			if (interrupted) {
				Thread.currentThread().interrupt();
			}
		}
	}

	private static void wrapAndThrowUnchecked(Throwable cause) {
		throw new UncheckedExecutionException(cause);
	}
}
