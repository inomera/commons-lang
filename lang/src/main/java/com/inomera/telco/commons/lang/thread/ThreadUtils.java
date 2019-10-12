package com.inomera.telco.commons.lang.thread;

public interface ThreadUtils {
	static void sleepQuietly(int sleepMs) {
		try {
			Thread.sleep(sleepMs);
		} catch (InterruptedException e) {
			// ignore
		}
	}
}
