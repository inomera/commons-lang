package com.inomera.telco.commons.lang;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Serdar Kuzucu
 */
class UuidUtilsTest {
    @Test
    void asString() {
        assertEquals(36, UuidUtils.asString().length());
    }

    @Test
    void asStringWithoutHyphen() {
        assertEquals(32, UuidUtils.asStringWithoutHyphen().length());
    }
}
