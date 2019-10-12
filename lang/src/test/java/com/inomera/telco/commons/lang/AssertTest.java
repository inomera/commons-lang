package com.inomera.telco.commons.lang;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Map;

import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link Assert} class.
 *
 * @author Keith Donald
 * @author Erwin Vervaet
 * @author Rick Evans
 * @author Arjen Poutsma
 * @author Sam Brannen
 * @author Juergen Hoeller
 */
class AssertTest {

	@Test
	void isTrueWithMessage() {
		Assert.isTrue(true, "enigma");
	}

	@Test
	void isTrue() {
		Assert.isTrue(true);
	}

	@Test
	void isTrueWithFalse() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isTrue(false));
		assertEquals("[Assertion failed] - this expression must be true", e.getMessage());
	}

	@Test
	void isTrueWithFalseAndMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isTrue(false, "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void isNull() {
		Assert.isNull(null);
	}

	@Test
	void isNullWithMessage() {
		Assert.isNull(null, "Bla");
	}

	@Test
	void isNullWithNonNullAndMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isNull(new Object(), "Bla"));
		assertEquals("Bla", e.getMessage());
	}

	@Test
	void isNullWithNonNull() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isNull(new Object()));
		assertEquals("[Assertion failed] - the object argument must be null", e.getMessage());
	}

	@Test
	void notNull() {
		Assert.notNull("foo");
	}

	@Test
	void notNullWithMessage() {
		Assert.notNull("foo", "enigma");
	}

	@Test
	void notNullWithNullValue() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notNull(null));
		assertEquals("[Assertion failed] - this argument is required; it must not be null", e.getMessage());
	}

	@Test
	void notNullWithMessageAndWithNullValue() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notNull(null, "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void hasLength() {
		Assert.hasLength("I Heart ...");
	}

	@Test
	void hasLengthWithMessage() {
		Assert.hasLength("I Heart ...", "enigma");
	}

	@Test
	void hasLengthWithWhitespaceOnly() {
		Assert.hasLength("\t  ", "enigma");
	}

	@Test
	void hasLengthWithEmptyString() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.hasLength("", "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void hasLengthWithNull() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.hasLength(null));
		assertEquals("[Assertion failed] - this String argument must have length; it must not be null or empty", e.getMessage());
	}

	@Test
	void hasLengthWithNullAndMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.hasLength(null, "enigma"));
		assertEquals("enigma", e.getMessage());
	}


	@Test
	void hasText() {
		Assert.hasText("foo");
	}

	@Test
	void hasTextWithWhitespaceOnly() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.hasText("\t "));
		assertEquals("[Assertion failed] - this String argument must have text; it must not be null, empty, or blank", e.getMessage());
	}

	@Test
	void hasTextWithMessage() {
		Assert.hasText("foo", "enigma");
	}

	@Test
	void hasTextWithWhitespaceOnlyAndWithMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.hasText("\t ", "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void hasTextWithEmptyString() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.hasText("", "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void hasTextWithNull() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.hasText(null, "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void doesNotContain() {
		Assert.doesNotContain("kilburn", "rod");
	}

	@Test
	void doesNotContainWithContainingSearchString() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.doesNotContain("kilburn", "burn"));
		assertEquals("[Assertion failed] - this String argument must not contain the substring [burn]", e.getMessage());
	}

	@Test
	void doesNotContainWithContainingSearchStringAndMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.doesNotContain("kilburn", "burn", "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void doesNotContainWithMessage() {
		Assert.doesNotContain("kilburn", "rod", "enigma");
	}

	@Test
	void doesNotContainWithNullSearchString() {
		Assert.doesNotContain(null, "rod", "enigma");
	}

	@Test
	void doesNotContainWithNullSubstring() {
		Assert.doesNotContain("A cool chick's name is Brod.", null, "enigma");
	}

	@Test
	void doesNotContainWithEmptySubstring() {
		Assert.doesNotContain("A cool chick's name is Brod.", "", "enigma");
	}

	@Test
	void doesNotContainWithNullSearchStringAndNullSubstring() {
		Assert.doesNotContain(null, null, "enigma");
	}

	@Test
	void notEmptyArray() {
		Assert.notEmpty(new String[]{"1234"});
	}

	@Test
	void notEmptyArrayWithMessage() {
		Assert.notEmpty(new String[]{"1234"}, "enigma");
	}

	@Test
	void notEmptyArrayWithEmptyArray() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty(new String[]{}));
		assertEquals("[Assertion failed] - this array must not be empty: it must contain at least 1 element", e.getMessage());
	}

	@Test
	void notEmptyArrayWithEmptyArrayAndMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty(new String[]{}, "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void notEmptyArrayWithNullArray() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty((Object[]) null, "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void noNullElements() {
		Assert.noNullElements(new String[]{"1234"});
	}

	@Test
	void noNullElementsWithMessage() {
		Assert.noNullElements(new String[]{"1234"}, "enigma");
	}

	@Test
	void noNullElementsWithNullElement() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.noNullElements(new String[]{"1234", null}));
		assertEquals("[Assertion failed] - this array must not contain any null elements", e.getMessage());
	}

	@Test
	void noNullElementsWithMessageAndNullElement() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.noNullElements(new String[]{"1234", null}, "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void noNullElementsWithEmptyArray() {
		Assert.noNullElements(new String[]{}, "enigma");
	}

	@Test
	void notEmptyCollection() {
		Assert.notEmpty(singletonList("foo"));
	}

	@Test
	void notEmptyCollectionWithMessage() {
		Assert.notEmpty(singletonList("foo"), "enigma");
	}

	@Test
	void notEmptyCollectionWithEmptyCollection() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty(emptyList()));
		assertEquals("[Assertion failed] - this collection must not be empty: it must contain at least 1 element", e.getMessage());
	}

	@Test
	void notEmptyCollectionWithEmptyCollectionAndMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty(emptyList(), "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void notEmptyCollectionWithNullCollection() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty((Collection<?>) null, "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void notEmptyMap() {
		Assert.notEmpty(singletonMap("foo", "bar"));
	}

	@Test
	void notEmptyMapWithMessage() {
		Assert.notEmpty(singletonMap("foo", "bar"), "enigma");
	}

	@Test
	void notEmptyMapWithNullMap() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty((Map<?, ?>) null));
		assertEquals("[Assertion failed] - this map must not be empty; it must contain at least one entry", e.getMessage());
	}

	@Test
	void notEmptyMapWithNullMapAndMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty((Map<?, ?>) null, "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void notEmptyMapWithEmptyMap() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty(emptyMap()));
		assertEquals("[Assertion failed] - this map must not be empty; it must contain at least one entry", e.getMessage());
	}

	@Test
	void notEmptyMapWithEmptyMapAndMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.notEmpty(emptyMap(), "enigma"));
		assertEquals("enigma", e.getMessage());
	}

	@Test
	void isInstanceOf() {
		Assert.isInstanceOf(String.class, "foo");
	}

	@Test
	void isInstanceOfWithMessage() {
		Assert.isInstanceOf(String.class, "foo", "enigma");
	}

	@Test
	void isInstanceOfWithNullType() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isInstanceOf(null, "foo", "enigma"));
		assertEquals("Type to check against must not be null", e.getMessage());
	}

	@Test
	void isInstanceOfWithNullInstance() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isInstanceOf(String.class, null, "enigma"));
		assertEquals("enigma Object of class [null] must be an instance of class java.lang.String", e.getMessage());
	}

	@Test
	void isInstanceOfWithTypeMismatchAndNullMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isInstanceOf(String.class, 42L, null));
		assertEquals("Object of class [java.lang.Long] must be an instance of class java.lang.String", e.getMessage());
	}

	@Test
	void isInstanceOfWithTypeMismatch() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isInstanceOf(String.class, 42L));
		assertEquals("Object of class [java.lang.Long] must be an instance of class java.lang.String", e.getMessage());
	}

	@Test
	void isInstanceOfWithTypeMismatchAndCustomMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isInstanceOf(String.class, 42L, "Custom message"));
		assertEquals("Custom message Object of class [java.lang.Long] must be an instance of class java.lang.String", e.getMessage());
	}

	@Test
	void isInstanceOfWithTypeMismatchAndCustomMessageWithSeparator() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isInstanceOf(String.class, 42L, "Custom message:"));
		assertEquals("Custom message: Object of class [java.lang.Long] must be an instance of class java.lang.String", e.getMessage());
	}

	@Test
	void isInstanceOfWithTypeMismatchAndCustomMessageWithSpace() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isInstanceOf(String.class, 42L, "Custom message for "));
		assertEquals("Custom message for  Object of class [java.lang.Long] must be an instance of class java.lang.String", e.getMessage());
	}

	@Test
	void isAssignable() {
		Assert.isAssignable(Number.class, Integer.class);
	}

	@Test
	void isAssignableWithMessage() {
		Assert.isAssignable(Number.class, Integer.class, "enigma");
	}

	@Test
	void isAssignableWithNullSupertype() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isAssignable(null, Integer.class, "enigma"));
		assertEquals("Type to check against must not be null", e.getMessage());
	}

	@Test
	void isAssignableWithNullSubtype() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isAssignable(Integer.class, null, "enigma"));
		assertEquals("enigma null is not assignable to class java.lang.Integer", e.getMessage());
	}

	@Test
	void isAssignableWithTypeMismatch() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isAssignable(String.class, Integer.class));
		assertEquals("class java.lang.Integer is not assignable to class java.lang.String", e.getMessage());
	}

	@Test
	void isAssignableWithTypeMismatchAndNullMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isAssignable(String.class, Integer.class, null));
		assertEquals("class java.lang.Integer is not assignable to class java.lang.String", e.getMessage());
	}

	@Test
	void isAssignableWithTypeMismatchAndCustomMessage() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isAssignable(String.class, Integer.class, "Custom message"));
		assertEquals("Custom message class java.lang.Integer is not assignable to class java.lang.String", e.getMessage());
	}

	@Test
	void isAssignableWithTypeMismatchAndCustomMessageWithSeparator() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isAssignable(String.class, Integer.class, "Custom message:"));
		assertEquals("Custom message: class java.lang.Integer is not assignable to class java.lang.String", e.getMessage());
	}

	@Test
	void isAssignableWithTypeMismatchAndCustomMessageWithSpace() {
		final IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Assert.isAssignable(String.class, Integer.class, "Custom message for "));
		assertEquals("Custom message for  class java.lang.Integer is not assignable to class java.lang.String", e.getMessage());
	}

	@Test
	void state() {
		Assert.state(true);
	}

	@Test
	void stateWithFalseExpression() {
		final IllegalStateException e = assertThrows(IllegalStateException.class, () -> Assert.state(false));
		assertEquals("[Assertion failed] - this state invariant must be true", e.getMessage());
	}

	@Test
	void stateWithMessage() {
		Assert.state(true, "enigma");
	}

	@Test
	void stateWithFalseExpressionAndMessage() {
		final IllegalStateException e = assertThrows(IllegalStateException.class, () -> Assert.state(false, "enigma"));
		assertEquals("enigma", e.getMessage());
	}
}
