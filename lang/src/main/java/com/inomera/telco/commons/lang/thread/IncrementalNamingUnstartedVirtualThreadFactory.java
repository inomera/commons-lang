package com.inomera.telco.commons.lang.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JDK 23 Virtual Thread supported Thread Factory
 */
public class IncrementalNamingUnstartedVirtualThreadFactory implements ThreadFactory {

    private static final Logger LOG = LoggerFactory.getLogger(IncrementalNamingUnstartedVirtualThreadFactory.class);

    private final String threadNamePrefix;
    private final AtomicInteger threadCount = new AtomicInteger(0);

    public IncrementalNamingUnstartedVirtualThreadFactory(String threadNamePrefix) {
        this.threadNamePrefix = (threadNamePrefix != null) ? threadNamePrefix : getDefaultThreadNamePrefix();
        LOG.debug("threadNamePrefix : {}", this.threadNamePrefix);
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return Thread.ofVirtual()
                .name(nextThreadName())
                .start(runnable);
    }

    private String nextThreadName() {
        return threadNamePrefix + threadCount.incrementAndGet();
    }

    private String getDefaultThreadNamePrefix() {
        return getClass().getSimpleName() + "-";
    }


}
