package com.inomera.telco.commons.lang.function;

/**
 * A strategy for handling errors. This is especially useful for handling
 * errors that occur during asynchronous execution of tasks that have been
 * submitted to a TaskScheduler. In such cases, it may not be possible to
 * throw the error to the original caller.
 *
 * @author Mark Fisher
 * @since 3.0
 */
@FunctionalInterface
public interface ErrorHandler {

    /**
     * Handle the given error, possibly rethrowing it as a fatal exception.
     */
    void handleError(Throwable t);

}
