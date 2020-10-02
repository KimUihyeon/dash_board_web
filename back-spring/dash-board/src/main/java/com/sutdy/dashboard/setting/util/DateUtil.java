package com.sutdy.dashboard.setting.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/**
 * @author kuh
 * @since 2020.05.08
 */
public class DateUtil {

    public enum Time {start, end}

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static Timestamp toTimeStamp(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return Timestamp.valueOf(LocalDateTime.parse(dateStr, formatter));
    }

    public static String toString(Timestamp timestamp, String format) {
        if (timestamp == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return timestamp.toLocalDateTime().format(dateTimeFormatter);
    }

    public static boolean compare(Timestamp timestamp1, Timestamp timestamp2, String format) {
        try {
            if (timestamp1 == null && timestamp2 == null) {
                return true;
            }
            return toString(timestamp1, format).equals(toString(timestamp2, format));
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static boolean compare(String dateStr1, String dateStr2, String format) {
        try {
            Timestamp t1 = toTimeStamp(dateStr1, format);
            Timestamp t2 = toTimeStamp(dateStr2, format);
            return compare(t1, t2, format);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static boolean compare(String dateStr1, Timestamp t2, String format) {
        try {
            Timestamp t1 = toTimeStamp(dateStr1, format);
            return compare(t1, t2, format);
        } catch (NullPointerException e) {
            return false;
        }
    }


    public static Timestamp now() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    public static String now(String format) {
        return toString(DateUtil.now(), format);
    }

    public static Timestamp firstDayOfMonth(int year, int month, Time time) {
        LocalDateTime ldt = null;

        if (time == Time.start)
            ldt = LocalDateTime.of(year, month, 1, 0, 0, 0);
        else
            ldt = LocalDateTime.of(year, month, 1, 23, 59, 59);

        return Timestamp.valueOf(ldt);
    }

    public static Timestamp lastDayOfMonth(int year, int month, Time time) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atEndOfMonth();

        LocalDateTime ldt = null;

        if (time == Time.start)
            ldt = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 0, 0, 0);
        else
            ldt = LocalDateTime.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), 23, 59, 59);

        return Timestamp.valueOf(ldt);
    }
}
