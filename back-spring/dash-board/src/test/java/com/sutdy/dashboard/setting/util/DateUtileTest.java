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
public class DateUtileTest {

    @Test
    public void stringDate_LocalDateTime_형변환_테스트(){

        // given
        // toString
        String dateStr = "2020-05-08T17:35:59.184";
        System.out.println(dateStr);

        // when
        // toLocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime parseDate  = LocalDateTime.parse(dateStr, formatter);


        // then
        System.out.println(parseDate.toLocalDate());


        Assert.assertTrue(dateStr.toString().equals(parseDate.toString()));

    }

    @Test
    public void stringDate_LocalDateTime_형변환_테스트2(){
        // given
        String date = "2020-05-08T17:35:59.184";
        // when
        LocalDateTime ld = DateUtil.stringToLocalDateTime(date,"yyyy-MM-dd'T'HH:mm:ss.SSS");
        ld.toString();

        // then
        Assert.assertTrue(date.equals(ld.toString()));

    }


    @Test
    public void dateTime_String_형변환_테스트() {

        // given
        // toString
        String dateStr = "2020-05-08T17:35:59.184";
        System.out.println(dateStr);

        // given
        // toLocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime parseDate  = LocalDateTime.parse(dateStr, formatter);

        System.out.println(parseDate.toLocalDate());


        // then
        Assert.assertTrue(dateStr.toString().equals(parseDate.toString()));
    }
}
