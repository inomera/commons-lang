package com.inomera.telco.commons.lang;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @author Serdar Kuzucu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringReplaceUtils {
    public static String replaceAll(String text, Map<String, String> searchAndReplacementTexts) {
        if (searchAndReplacementTexts == null || searchAndReplacementTexts.isEmpty() || StringUtils.isBlank(text)) {
            return text;
        }

        for (Map.Entry<String, String> searchAndReplacementText : searchAndReplacementTexts.entrySet()) {
            final String searchText = searchAndReplacementText.getKey();
            final String replacementText = searchAndReplacementText.getValue();
            text = StringUtils.replace(text, searchText, replacementText);
        }

        return text;
    }

    public static String replaceAllDefaultIfBlank(String text, Map<String, String> searchAndReplacementPairs, String defaultValue, String pattern) {
        final String replacedText = replaceAllDefaultIfBlank(text, searchAndReplacementPairs, defaultValue);
        return replacedText.replaceAll(pattern, defaultValue);
    }

    public static String replaceAllDefaultIfBlank(String text, Map<String, String> searchAndReplacementPairs, String defaultValue) {
        for (Map.Entry<String, String> nameValuePair : searchAndReplacementPairs.entrySet()) {
            text = replaceDefaultIfBlank(text, nameValuePair.getKey(), nameValuePair.getValue(), defaultValue);
        }
        return text;
    }

    public static String replaceDefaultIfBlank(String text, String search, String replacement, String defaultValue) {
        final String replaceValue = StringUtils.defaultIfBlank(replacement, defaultValue);
        return StringUtils.replace(text, search, replaceValue);
    }

    public static String convertTurkishCharactersToEnglish(String value) {
        value = StringUtils.defaultString(value);
        final StringBuilder sb = new StringBuilder(value.length());
        for (int i = 0; i < value.length(); i++) {
            final char character = value.charAt(i);
            sb.append(replaceTurkishCharacter(character));
        }
        return sb.toString();
    }

    private static char replaceTurkishCharacter(char character) {
        switch (character) {
            case '\u011f':
                return 'g';
            case '\u011e':
                return 'G';
            case '\u0131':
                return 'i';
            case '\u0130':
                return 'I';
            case '\u00f6':
                return 'o';
            case '\u00d6':
                return 'O';
            case '\u00fc':
                return 'u';
            case '\u00dc':
                return 'U';
            case '\u015f':
                return 's';
            case '\u015e':
                return 'S';
            case '\u00e7':
                return 'c';
            case '\u00c7':
                return 'C';
        }
        return character;
    }
}
