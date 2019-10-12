package com.inomera.telco.commons.lang;

import lombok.Getter;

import java.util.Calendar;

/**
 * @author Serdar Kuzucu
 */
@Getter
public enum UnitOfTime {
    MS(1, Calendar.MILLISECOND, 1),
    SEC(1, Calendar.SECOND, 1000),
    MIN(2, Calendar.MINUTE, 1000 * 60),
    HOUR(3, Calendar.HOUR, 1000 * 60 * 60),
    DAY(4, Calendar.DAY_OF_MONTH, 1000 * 60 * 60 * 24),
    WEEK(5, Calendar.WEEK_OF_YEAR, 1000 * 60 * 60 * 24 * 7),
    MONTH(6, Calendar.MONTH, 1000L * 60 * 60 * 24 * 30L),
    YEAR(7, Calendar.YEAR, (365L * 24 + 6) * 1000 * 60 * 60L);

    private final int order;
    private final int calendarField;
    private final long milliseconds;

    UnitOfTime(int order, int calendarField, long milliseconds) {
        this.order = order;
        this.calendarField = calendarField;
        this.milliseconds = milliseconds;
    }

    public static UnitOfTime fromString(String value) {
        for (UnitOfTime unitOfTime : values()) {
            if (unitOfTime.name().equalsIgnoreCase(value)) {
                return unitOfTime;
            }
        }
        return null;
    }

    public long convertToMillis(long duration) {
        return duration * milliseconds;
    }

    public long convertToMillis() {
        return milliseconds;
    }
}
