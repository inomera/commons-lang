package com.inomera.telco.commons.lang;

import com.inomera.telco.commons.lang.function.ThrowableSupplier;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Serdar Kuzucu
 */
class ThrowableSupplierTest {
	@Test
	void shouldConvertExceptionToRuntimeException() {
		final ThrowableSupplier<String> throwableSupplier = () -> {
			throw new Exception("Error!");
		};
		final Supplier<String> supplier = ThrowableSupplier.toSupplier(throwableSupplier);
		final RuntimeException e = assertThrows(RuntimeException.class, supplier::get);
		assertEquals("Error!", e.getMessage());
	}

	@Test
	void shouldReturnValueSuccessfully() {
		final ThrowableSupplier<String> throwableSupplier = () -> "Demo";
		final Supplier<String> supplier = ThrowableSupplier.toSupplier(throwableSupplier);
		assertEquals("Demo", supplier.get());
	}
}
