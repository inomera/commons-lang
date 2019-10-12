package com.inomera.telco.commons.lang.function;

import java.util.function.Predicate;

/**
 * @author Serdar Kuzucu
 */
@FunctionalInterface
public interface ThrowablePredicate<T> {
	boolean test(T t) throws Exception;

	static <T> Predicate<T> toPredicate(ThrowablePredicate<T> throwablePredicate) {
		return (t) -> {
			try {
				return throwablePredicate.test(t);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		};
	}
}
