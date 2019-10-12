package com.inomera.telco.commons.lang.function;

import java.util.function.IntFunction;

/**
 * @author Serdar Kuzucu
 */
@FunctionalInterface
public interface ThrowableIntFunction<R> {
	R apply(int value) throws Exception;

	static <R> IntFunction<R> toIntFunction(ThrowableIntFunction<R> throwableIntFunction) {
		return (i) -> {
			try {
				return throwableIntFunction.apply(i);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		};
	}
}
