package com.inomera.telco.commons.lang;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.inomera.telco.commons.lang.PatternUtils.getValueByPattern;
import static com.inomera.telco.commons.lang.PatternUtils.removeAllNonEnglishAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Serdar Kuzucu
 */
class PatternUtilsTest {
    @Test
    void getValueByPatternShouldReturnValidPartOfString() {
        final String result = getValueByPattern("Hello <b>World</b>!", Pattern.compile("<b>(.*)</b>"));
        assertEquals("World", result);
    }

    @Test
    void getValueByPatternShouldReturnFirstMatch() {
        final String result = getValueByPattern("Hello <b>World</b>! Glad <b>to</b> see you.", Pattern.compile("<b>([^<]*)</b>"));
        assertEquals("World", result);
    }

    @Test
    void getValueByPatternShouldReturnNullIfNoMatchIsFound() {
        final String result = getValueByPattern("Hello World! Glad to see you.", Pattern.compile("<b>(.*)</b>"));
        assertNull(result);
    }

    @Test
    void removeAllNonEnglishAlphanumericShouldRemoveSpecialCharactersAndTurkishCharacters() {
        final String value = "AaBbCc\u00c7\u00e7Dd?EeFfGg\u011e\u011fHhI\u0131Jj\u0130iKkLlMmNnOo\u00d6\u00f6PpQqRrSs\u015e\u015fTtUu\u00dc\u00fcVvWwXxYy_Zz";
        final String result = removeAllNonEnglishAlphanumeric(value);
        final String expected = "AaBbCcDdEeFfGgHhIJjiKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        assertEquals(expected, result);
    }
}
