package com.sutdy.dashboard.setting.util;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author kuh
 * @since 2020.05.08
 */

@SpringBootTest
public class DateUtileTest {

    private final Logger logger = LoggerFactory.getLogger(DateUtileTest.class);

    @Test
    public void stringDate_Timestamp_형변환_테스트() {

        // given
        // toString
        String dateStr = "2020-05-08 17:35:59.184";
        System.out.println(dateStr);

        // when
        // toTimestamp
        Timestamp parseTimeStamp = Timestamp.valueOf(dateStr);
        String parseDate = DateUtil.toString(parseTimeStamp, "yyyy-MM-dd HH:mm:ss.SSS");


        // then

        Assert.assertTrue(dateStr.equals(parseDate));

    }

    @Test
    public void stringDate_Timestamp_형변환_테스트2() {
        // given
        String date = "2020-05-08 17:35:59.184";
        // when
        Timestamp ld = DateUtil.toTimeStamp(date, "yyyy-MM-dd HH:mm:ss.SSS");
        ld.toString();

        // then
        Assert.assertTrue(date.equals(ld.toString()));

    }


    @Test
    public void dateTime_String_형변환_테스트() {

        // given
        // toString
        String dateStr = "2020-05-08 17:35:59.184";
        System.out.println(dateStr);

        // given
        // toTimestamp
        Timestamp parseTimeStamp = Timestamp.valueOf(dateStr);
        String parseDate = DateUtil.toString(parseTimeStamp, "yyyy-MM-dd HH:mm:ss.SSS");

        // then
        Assert.assertTrue(dateStr.equals(parseDate));
    }

    @Test
    public void dateTime_TimeStamp_형변환_테스트() {

        // given
        // toString
        String dateStr = "2020-05-08 17:35:59.184";
        System.out.println(dateStr);

        LocalDateTime ldt = LocalDateTime.now();


        // given
        // toTimestamp
        Timestamp parseTimeStamp = Timestamp.valueOf(ldt);
        String parseDate = DateUtil.toString(parseTimeStamp, "yyyy-MM-dd HH:mm:ss.SSS");

        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append(Timestamp.valueOf(ldt).toString());
        sb.append(System.lineSeparator());
        sb.append(parseTimeStamp.toString());

        logger.info(sb.toString());

        // then
        Assert.assertTrue(Timestamp.valueOf(ldt).toString().equals(parseTimeStamp.toString()));
    }


    @Test
    public void dateTime_TimeStamp_포맷_비교_테스트() {

        // Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]
        // Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]
        // Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]
        // Timestamp format must be 항상 위 포맷이어야함 .

        // given
        // toString
        String dateStr1 = "2020-05-08 17:35:59.184";
        String dateStr3 = "2020-05-08 00:00:00.000";

        // given
        // toTimestamp
        String dateTimestamp1 = Timestamp.valueOf(dateStr1).toString();
        String dateTimestamp3 = Timestamp.valueOf(dateStr3).toString();

        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append(dateTimestamp1);
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());
        sb.append(dateTimestamp3);
        sb.append(System.lineSeparator());
        sb.append(System.lineSeparator());

        logger.info(sb.toString());

        // then
        Assert.assertNotEquals(dateTimestamp1, dateTimestamp3);
    }
}
