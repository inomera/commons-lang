package com.inomera.telco.commons.lang;

import com.inomera.telco.commons.lang.function.ThrowableIntFunction;
import org.junit.jupiter.api.Test;

import java.util.function.IntFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Serdar Kuzucu
 */
class ThrowableIntFunctionTest {

	@Test
	void shouldConvertExceptionToRuntimeException() {
		final ThrowableIntFunction<String> throwableIntFunction = intValue -> {
			throw new Exception("Some message " + intValue);
		};
		final IntFunction<String> intFunction = ThrowableIntFunction.toIntFunction(throwableIntFunction);

		final RuntimeException e = assertThrows(RuntimeException.class, () -> intFunction.apply(5));
		assertEquals("Some message 5", e.getMessage());
	}

	@Test
	void shouldReturnValueSuccessfully() {
		final ThrowableIntFunction<String> throwableIntFunction = intValue -> "Value: " + intValue;
		final IntFunction<String> intFunction = ThrowableIntFunction.toIntFunction(throwableIntFunction);

		assertEquals("Value: 5", intFunction.apply(5));
	}

}
