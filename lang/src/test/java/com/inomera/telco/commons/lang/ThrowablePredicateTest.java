package com.inomera.telco.commons.lang;

import com.inomera.telco.commons.lang.function.ThrowablePredicate;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Serdar Kuzucu
 */
class ThrowablePredicateTest {

	@Test
	void shouldConvertExceptionToRuntimeException() {
		final ThrowablePredicate<String> throwablePredicate = value -> {
			throw new Exception("Error: " + value);
		};

		final Predicate<String> predicate = ThrowablePredicate.toPredicate(throwablePredicate);

		final RuntimeException e = assertThrows(RuntimeException.class, () -> predicate.test("test"));
		assertEquals("Error: test", e.getMessage());
	}

	@Test
	void shouldReturnTrueValueSuccessfully() {
		final ThrowablePredicate<String> throwablePredicate = value -> true;
		final Predicate<String> predicate = ThrowablePredicate.toPredicate(throwablePredicate);
		assertTrue(predicate.test("test"));
	}

	@Test
	void shouldReturnFalseValueSuccessfully() {
		final ThrowablePredicate<String> throwablePredicate = value -> false;
		final Predicate<String> predicate = ThrowablePredicate.toPredicate(throwablePredicate);
		assertFalse(predicate.test("test"));
	}

}
