package com.inomera.telco.commons.lang;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Serdar Kuzucu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UuidUtils {
    public static String asStringWithoutHyphen() {
        return asString().replace("-", "");
    }

    public static String asString() {
        return UUID.randomUUID().toString();
    }
}
