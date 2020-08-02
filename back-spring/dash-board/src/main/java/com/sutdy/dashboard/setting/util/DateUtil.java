package com.sutdy.dashboard.setting.util;

import com.sutdy.dashboard.setting.ApplicationStringConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author kuh
 * @since 2020.05.08
 */
public class DateUtil {

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
}
