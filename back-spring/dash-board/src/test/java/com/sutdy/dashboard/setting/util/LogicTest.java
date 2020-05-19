package com.sutdy.dashboard.setting.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author kuh
 * @since 2020.05.08
 */

@SpringBootTest
public class LogicTest {

    @Test
    public void stringToDateTimeTest(){
        LocalDateTime ld = LocalDateTime.now();

        // toString
        String dateStr = "2020-05-08T17:35:59.184";
        System.out.println(dateStr);

        // toLocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime parseDate  = LocalDateTime.parse(dateStr, formatter);

        System.out.println(parseDate.toLocalDate());




        Assert.assertTrue(ld.toString().equals(parseDate.toString()));

    }

    @Test
    public void utilFunctionTest(){
        String date = "2020-05-08T17:35:59.184";
        LocalDateTime ld = DateUtil.stringToLocalDateTime(date,"yyyy-MM-dd'T'HH:mm:ss.SSS");
        ld.toString();

        Assert.assertTrue(date.equals(ld.toString()));

    }


    @Test
    public void dateTimeToStringTest() {

        LocalDateTime ld = LocalDateTime.now();

        // toString
        String dateStr = "2020-05-08T17:35:59.184";
        System.out.println(dateStr);

        // toLocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime parseDate  = LocalDateTime.parse(dateStr, formatter);

        System.out.println(parseDate.toLocalDate());




        Assert.assertTrue(ld.toString().equals(parseDate.toString()));
    }
}
