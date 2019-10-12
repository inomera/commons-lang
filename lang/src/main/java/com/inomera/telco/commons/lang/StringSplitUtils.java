package com.inomera.telco.commons.lang;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author Serdar Kuzucu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringSplitUtils {
    public static List<String> byLength(String value, int maxPartLength) {
        if (value == null || value.isEmpty() || maxPartLength < 1) {
            return new ArrayList<>();
        }

        final List<String> result = new ArrayList<>();
        for (int start = 0; start < value.length(); start += maxPartLength) {
            result.add(value.substring(start, Math.min(value.length(), start + maxPartLength)));
        }
        return result;
    }

    public static List<String> asList(String value, String separator) {
        final String[] resultAsArray = StringUtils.split(value, separator);
        if (resultAsArray == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(resultAsArray));
    }

    public static Set<String> asSet(String value, String separator) {
        final String[] resultAsArray = StringUtils.split(value, separator);
        if (resultAsArray == null) {
            return new HashSet<>();
        }
        return new HashSet<>(Arrays.asList(resultAsArray));
    }
}
