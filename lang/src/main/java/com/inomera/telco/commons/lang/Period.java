package com.inomera.telco.commons.lang;

import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author Serdar Kuzucu
 */
@Getter
@SuppressWarnings("MagicConstant")
public class Period implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Period ZERO = new Period(0, UnitOfTime.SEC);

    private final Integer value;
    private final UnitOfTime unit;

    public Period(Integer value, UnitOfTime unit) {
        this.value = value;
        this.unit = unit;
    }

    public long asMilliseconds() {
        return unit.convertToMillis(value);
    }

    public long asHoursCeiling() {
        final double milliseconds = asMilliseconds();
        final double anHour = UnitOfTime.HOUR.convertToMillis();
        return (long) Math.ceil(milliseconds / anHour);
    }

    public Date appendTo(Date dateToAppend) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToAppend);
        calendar.add(unit.getCalendarField(), value);
        return calendar.getTime();
    }

    public Calendar appendTo(Calendar calendarToAppend) {
        final Calendar calendar = (Calendar) calendarToAppend.clone();
        calendar.add(unit.getCalendarField(), value);
        return calendar;
    }

    public Date subtractFrom(Date date) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(unit.getCalendarField(), -1 * value);
        return calendar.getTime();
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Period period = (Period) o;
        return Objects.equals(value, period.value) && unit == period.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unit);
    }

    public boolean isZero() {
        return value == 0;
    }

    public static Period of(Integer value, UnitOfTime unit) {
        return new Period(value, unit);
    }

    public static Period createNullable(Integer value, UnitOfTime unit) {
        if (unit == null || value == null) {
            return null;
        }
        return Period.of(value, unit);
    }

    public static Period zeroIfNull(Period period) {
        return ObjectUtils.defaultIfNull(period, ZERO);
    }

    public static Period month(int amount) {
        return new Period(amount, UnitOfTime.MONTH);
    }

    public static Period minute(int amount) {
        return new Period(amount, UnitOfTime.MIN);
    }

    public static Period week(int amount) {
        return new Period(amount, UnitOfTime.WEEK);
    }

    public static Period hour(int amount) {
        return new Period(amount, UnitOfTime.HOUR);
    }

    public static Period millis(int amount) {
        return new Period(amount, UnitOfTime.MS);
    }

    public static Period days(int amount) {
        return new Period(amount, UnitOfTime.DAY);
    }

    public static Period difference(Date larger, Date smaller) {
        return Period.millis((int) (larger.getTime() - smaller.getTime()));
    }

    public static Period fromCommaSeparatedStringSafe(String period) {
        if (period == null || !period.contains(",")) {
            return null;
        }

        String[] split = period.split(",");
        if (split.length != 2 || split[0].length() == 0 || split[1].length() == 0) {
            return null;
        }

        try {
            return Period.createNullable(Integer.parseInt(split[0]), UnitOfTime.fromString(split[1]));
        } catch (Exception e) {
            return null;
        }
    }
}
