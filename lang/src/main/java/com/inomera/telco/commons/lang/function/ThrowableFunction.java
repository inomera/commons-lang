package com.inomera.telco.commons.lang.function;

/**
 * Represents a function that accepts one argument and produces a result and can throw an exception.
 *
 * <p>This is a functional interface
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 * @author Serdar Kuzucu
 */
@FunctionalInterface
public interface ThrowableFunction<T, R> {
	/**
	 * Applies this function to the given argument.
	 *
	 * @param t the function argument
	 * @return the function result
	 * @throws Exception if any occurs
	 */
	R apply(T t) throws Exception;
}
