package com.sutdy.dashboard.setting.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author kuh
 * @since 2020.05.08
 */
public class Util {

    public static LocalDateTime stringToLocalDateTime(String dateStr, String format){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateStr, formatter);
    }


    public static String localDateTimeToString(LocalDateTime localDateTime , String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(dateTimeFormatter);
    }
}
