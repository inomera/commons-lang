package com.inomera.telco.commons.lang.function;

import java.util.function.Supplier;

/**
 * @author Serdar Kuzucu
 */
@FunctionalInterface
public interface ThrowableSupplier<T> {
	T get() throws Exception;

	static <T> Supplier<T> toSupplier(ThrowableSupplier<T> throwableSupplier) {
		return () -> {
			try {
				return throwableSupplier.get();
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		};
	}
}
