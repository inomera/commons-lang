package com.inomera.telco.commons.lang.fault;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author Serdar Kuzucu
 */
class UncheckedExecutionExceptionTest {
	@Test
	void shouldGetCause() {
		final IllegalArgumentException cause = new IllegalArgumentException("Arg1 is illegal");
		final UncheckedExecutionException e = new UncheckedExecutionException(cause);
		assertSame(cause, e.getCause());
	}
}
