package com.inomera.telco.commons.lang;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.inomera.telco.commons.lang.StringReplaceUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Serdar Kuzucu
 */
class StringReplaceUtilsTest {
    @Test
    void shouldFindAndReplace() {
        final Map<String, String> findAndReplaceMap = new HashMap<>();
        findAndReplaceMap.put("OLD_PRICE", "12");
        findAndReplaceMap.put("NEW_PRICE", "13");

        final String result = replaceAll("We will update OLD_PRICE as NEW_PRICE soon", findAndReplaceMap);
        assertEquals("We will update 12 as 13 soon", result);
    }

    @Test
    void shouldFindAndReplaceWhenMapIsNull() {
        final String result = replaceAll("We will update OLD_PRICE as NEW_PRICE soon", null);
        assertEquals("We will update OLD_PRICE as NEW_PRICE soon", result);
    }

    @Test
    void shouldFindAndReplaceWhenTextIsNull() {
        final Map<String, String> findAndReplaceMap = new HashMap<>();
        findAndReplaceMap.put("OLD_PRICE", "12");
        findAndReplaceMap.put("NEW_PRICE", "13");

        final String result = replaceAll(null, findAndReplaceMap);
        assertNull(result);
    }

    @Test
    void shouldFindAndReplaceWhenTextIsEmpty() {
        final Map<String, String> findAndReplaceMap = new HashMap<>();
        findAndReplaceMap.put("OLD_PRICE", "12");
        findAndReplaceMap.put("NEW_PRICE", "13");

        final String result = replaceAll("", findAndReplaceMap);
        assertEquals("", result);
    }

    @Test
    void shouldReturnTextIfTextIsBlank() {
        String actualText = replaceDefaultIfBlank(null, "{MERSIS}", "23561231", StringUtils.EMPTY);
        assertNull(actualText);
    }

    @Test
    void shouldReturnTextIfNameIsBlank() {
        String actualText = replaceDefaultIfBlank("current text", "", "23561231", StringUtils.EMPTY);
        assertEquals("current text", actualText);
    }

    @Test
    void shouldReplaceDefaultIfEmptyDefaultValueGivenTextIfValueIsNull() {
        String actualText = replaceDefaultIfBlank("Unvan : {UNVAN}", "{UNVAN}", null, "Turgay");

        assertEquals("Unvan : Turgay", actualText);
    }

    @Test
    void shouldReplaceDefaultIfEmptyValueGivenTextIfFoundName() {
        String actualText = replaceDefaultIfBlank("Unvan : {UNVAN}", "{UNVAN}", "Turgay", StringUtils.EMPTY);
        assertEquals("Unvan : Turgay", actualText);
    }

    @Test
    void shouldReplaceDefaultIfEmptyValueGivenTextIfSomeOfGivenNamesAndValuesExist() {
        final Map<String, String> searchAndReplacements = new HashMap<>();
        searchAndReplacements.put("{UNVAN}", "Turgay Can1");
        searchAndReplacements.put("{MERSIS}", "2345121");
        searchAndReplacements.put("{ADRES}", "Bak\u0131rk\u00f6y");
        final String actualText = replaceAllDefaultIfBlank("Unvan : {UNVAN}, Mersis : {MERSIS}, Adres : {ADRES}",
                searchAndReplacements, StringUtils.EMPTY);
        assertEquals("Unvan : Turgay Can1, Mersis : 2345121, Adres : Bak\u0131rk\u00f6y", actualText);
    }

    @Test
    void shouldReplaceDefaultIfEmptyValueGivenTextIfSomeOfGivenNamesAndValuesExistAndSomeOfThemNot() {
        final Map<String, String> searchAndReplacements = new HashMap<>();
        searchAndReplacements.put("{UNVAN}", "Turgay Can");
        searchAndReplacements.put("{MERSIS}", "234512");
        searchAndReplacements.put("{ADRES2}", "Turgay Can");

        final String actualText = replaceAllDefaultIfBlank("Unvan : {UNVAN}, Mersis : {MERSIS}, Adres : {ADRES}",
                searchAndReplacements, StringUtils.EMPTY, "([{])(\\w+|\\W+)([}])*");

        assertEquals("Unvan : Turgay Can, Mersis : 234512, Adres : ", actualText);
    }

    @Test
    void convertTurkishCharactersToEnglishShouldConvertSuccessfully() {
        final String value = "AaBbCc\u00c7\u00e7DdEeFfGg\u011e\u011fHhI\u0131\u0130iJjKkLlMmNnOo\u00d6\u00f6PpQqRrSs\u015e\u015fTtUu\u00dc\u00fcVvWwXxYyZz";
        final String result = convertTurkishCharactersToEnglish(value);
        final String expected = "AaBbCcCcDdEeFfGgGgHhIiIiJjKkLlMmNnOoOoPpQqRrSsSsTtUuUuVvWwXxYyZz";
        assertEquals(expected, result);
    }
}
