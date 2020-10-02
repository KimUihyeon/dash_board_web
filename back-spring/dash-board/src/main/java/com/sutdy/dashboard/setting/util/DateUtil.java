package com.sutdy.dashboard.setting.util;

import com.sutdy.dashboard.setting.ApplicationStringConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static LocalDateTime stringToLocalDateTime(String dateStr, String format) {
        if (dateStr == null) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }

    public static boolean dateTimeCompare(LocalDateTime localDateTime1, LocalDateTime localDateTime2, String format) {
        try {
            if (localDateTime1 == null && localDateTime2 == null) {
                return true;
            }
            return localDateTimeToString(localDateTime1, format).equals(localDateTimeToString(localDateTime2, format));
        } catch (NullPointerException e) {
            logger.error(e.getStackTrace().toString());
            return false;
        }
    }

    public static boolean dateTimeCompare(String localDateTime1, LocalDateTime localDateTime2, String format) {
        try {
            LocalDateTime localDateTime_1 = stringToLocalDateTime(localDateTime1, format);
            return dateTimeCompare(localDateTime_1, localDateTime2, format);
        } catch (NullPointerException e) {
            logger.error(e.getStackTrace().toString());
            return false;
        }
    }


    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    public static String now(String format) {
        return localDateTimeToString(DateUtil.now(), format);
    }

    public static LocalDateTime firstDayOfMonth(int year, int month, Time time) {
        switch (time) {
            case start: {
                return LocalDateTime.of(year, month, 1,
                        0, 0, 0 );
            }
            case end:
            default: {
                return LocalDateTime.of(year, month, 1,
                        23, 59, 59);
            }
        }
    }

    public static LocalDateTime lastDayOfMonth(int year, int month, Time time) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atEndOfMonth();

        switch (time) {
            case start: {
                return LocalDateTime.of(
                        localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(),
                        0, 0, 0);
            }
            case end:
            default: {
                return LocalDateTime.of(
                        localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(),
                        23, 59, 59);
            }
        }
    }
}
