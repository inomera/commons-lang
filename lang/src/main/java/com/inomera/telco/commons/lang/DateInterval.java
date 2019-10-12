package com.inomera.telco.commons.lang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.time.DateUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author Serdar Kuzucu
 */
@Getter
@AllArgsConstructor
public class DateInterval implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Date startDate;
    private final Date endDate;

    public static DateInterval of(Date startDate, Date endDate) {
        return new DateInterval(startDate, endDate);
    }

    public static DateInterval of(Long startDate, Long endDate) {
        return new DateInterval(new Date(startDate), new Date(endDate));
    }

    public boolean hasStartDate() {
        return startDate != null;
    }

    public boolean hasEndDate() {
        return endDate != null;
    }

    public boolean matches(Date date) {
        return startDate.getTime() <= date.getTime() && endDate.getTime() >= date.getTime();
    }

    public boolean after(Date date) {
        return date.getTime() < startDate.getTime();
    }

    public boolean before(Date date) {
        return date.getTime() > endDate.getTime();
    }

    public static DateInterval fromNDaysAgo(Date endDate, int days) {
        final Date aYearAgo = DateUtils.addDays(endDate, -days);
        return of(aYearAgo, endDate);
    }

    public static DateInterval fromBeginningOfMonthToEndOfMonth(Date date) {
        final Date beginning = DateUtils.truncate(date, Calendar.MONTH);
        final Date end = DateUtils.addMonths(beginning, 1);
        return new DateInterval(beginning, DateUtils.addMilliseconds(end, -1));
    }

    public static DateInterval fromBeginningOfMonthToCurrentDay(Date date) {
        final Date beginning = DateUtils.truncate(date, Calendar.MONTH);
        final Date end = DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
        return new DateInterval(beginning, end);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DateInterval [startDate=");
        builder.append(startDate);
        builder.append(", endDate=");
        builder.append(endDate);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DateInterval that = (DateInterval) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
