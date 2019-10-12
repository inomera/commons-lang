package com.inomera.telco.commons.lang;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.function.Supplier;

public class TextUtils {
    public static String generateDefaultIfBlank(String value, Supplier<String> generateFunction) {
        return Optional.ofNullable(value).filter(StringUtils::isNotBlank).orElseGet(generateFunction);
    }
}
