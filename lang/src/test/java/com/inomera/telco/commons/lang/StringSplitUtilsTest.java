package com.inomera.telco.commons.lang;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Serdar Kuzucu
 */
class StringSplitUtilsTest {
    @Test
    void byLengthShouldSplitByLength() {
        final String longString = RandomStringUtils.randomAlphanumeric(512);
        final List<String> stringsSplitByLength = StringSplitUtils.byLength(longString, 100);

        assertEquals(6, stringsSplitByLength.size());
        assertEquals(100, stringsSplitByLength.get(0).length());
        assertEquals(100, stringsSplitByLength.get(1).length());
        assertEquals(100, stringsSplitByLength.get(2).length());
        assertEquals(100, stringsSplitByLength.get(3).length());
        assertEquals(100, stringsSplitByLength.get(4).length());
        assertEquals(12, stringsSplitByLength.get(5).length());
        assertEquals(StringUtils.join(stringsSplitByLength.toArray()), longString);
    }

    @Test
    void byLengthShouldReturnSingleElementListWhenMaxLengthIsLargerThanLength() {
        final String shortString = RandomStringUtils.randomAlphanumeric(43);
        final List<String> stringsSplitByLength = StringSplitUtils.byLength(shortString, 100);

        assertEquals(1, stringsSplitByLength.size());
        assertEquals(43, stringsSplitByLength.get(0).length());
        assertEquals(stringsSplitByLength.get(0), shortString);
    }

    @Test
    void byLengthShouldReturnEmptyListWhenArgumentIsNull() {
        assertTrue(StringSplitUtils.byLength(null, 100).isEmpty());
    }

    @Test
    void byLengthShouldReturnEmptyListWhenArgumentIsEmptyString() {
        assertTrue(StringSplitUtils.byLength("", 100).isEmpty());
    }

    @Test
    void byLengthShouldReturnEmptyListWhenMaxLengthIsZeroOrNegative() {
        assertTrue(StringSplitUtils.byLength("abcDEF", 0).isEmpty());
        assertTrue(StringSplitUtils.byLength("abcDEF", -1).isEmpty());
    }

    @Test
    void asListShouldSplitIntoList() {
        final List<String> stringsSplitByComma = StringSplitUtils.asList("a,b,c,d,e", ",");
        assertEquals(5, stringsSplitByComma.size());
        assertEquals("a", stringsSplitByComma.get(0));
        assertEquals("b", stringsSplitByComma.get(1));
        assertEquals("c", stringsSplitByComma.get(2));
        assertEquals("d", stringsSplitByComma.get(3));
        assertEquals("e", stringsSplitByComma.get(4));
    }

    @Test
    void asListShouldReturnEmptyListWhenArgumentIsNullOrEmpty() {
        assertTrue(StringSplitUtils.asList(null, ",").isEmpty());
        assertTrue(StringSplitUtils.asList("", ",").isEmpty());
    }

    @Test
    void asListShouldReturnSingleItemListWhenSeparatorNotInText() {
        final List<String> result = StringSplitUtils.asList("demo", ",");
        assertEquals(1, result.size());
        assertEquals("demo", result.get(0));
    }

    @Test
    void asSetShouldSplitIntoList() {
        final Set<String> expected = new HashSet<>(Arrays.asList("a", "b", "c", "d", "e"));

        final Set<String> stringsSplitByComma = StringSplitUtils.asSet("a,b,c,d,e", ",");
        assertEquals(5, stringsSplitByComma.size());
        assertEquals(expected, stringsSplitByComma);
    }

    @Test
    void asSetShouldReturnEmptyListWhenArgumentIsNullOrEmpty() {
        assertTrue(StringSplitUtils.asSet(null, ",").isEmpty());
        assertTrue(StringSplitUtils.asSet("", ",").isEmpty());
    }

    @Test
    void asSetShouldReturnSingleItemListWhenSeparatorNotInText() {
        final Set<String> result = StringSplitUtils.asSet("demo", ",");
        assertEquals(1, result.size());
        assertEquals("demo", result.iterator().next());
    }
}
