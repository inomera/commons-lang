package com.inomera.telco.commons.lang;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Serdar Kuzucu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PatternUtils {
    private static final Pattern ENGLISH_ALPHANUMERIC = Pattern.compile("([^a-zA-Z0-9])");

    public static String getValueByPattern(String value, Pattern pattern) {
        final Matcher headMatcher = pattern.matcher(value);
        if (headMatcher.find()) {
            return headMatcher.group(1);
        }
        return null;
    }

    public static String removeAllNonEnglishAlphanumeric(String value) {
        return ENGLISH_ALPHANUMERIC.matcher(value).replaceAll("");
    }
}
