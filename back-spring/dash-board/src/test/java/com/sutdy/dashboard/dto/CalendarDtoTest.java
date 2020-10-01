package com.sutdy.dashboard.dto;


import com.sutdy.dashboard.domain.calendars.Calendar;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.SecurityStringUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * dto - entity
 * modelMapper Test
 * @author kuh
 * @since 08.20.03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CalendarDtoTest {

    private final Logger logger = LoggerFactory.getLogger(CalendarDtoTest.class);

    @Test
    public void CalendarDto_엔티티_컨버팅_테스트(){

        //given
        String pw = "";
        String cDate = DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT);
        logger.info(cDate);
        try {
            pw = SecurityStringUtil.encryptMD5("123123");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setId("accountDtoTest@naver.com");
        accountDto.setName("테스트 이름");
        accountDto.setCDate(cDate);
        accountDto.setPw(pw);


        Calendar calendar = Calendar.builder()
                .title("1")
                .cDate(DateUtil.now())
                .color("#fff")
                .account(accountDto.toEntity())
                .description("테스트 설명")
                .build();

        //when
        CalendarDto calendarDto = new CalendarDto().of(calendar);
        if(calendar.getAccount() != null){
            calendarDto.setAccountId(calendar.getAccount().getId());
        }

        //then
        Assert.assertEquals(calendarDto.getAccountId(), calendar.getAccount().getId());
        Assert.assertEquals(calendarDto.getDescription(), calendar.getDescription());
        Assert.assertEquals(calendarDto.getTitle(), calendar.getTitle());
        Assert.assertEquals(calendarDto.getColor(), calendar.getColor());
        Assert.assertEquals(calendarDto.getId(), calendar.getId());
        Assert.assertEquals(calendarDto.getId(), calendar.getId());
    }

    @Test
    public void CalendarDto_컨버팅_테스트(){

        //given
        String pw = "";
        String cDate = DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT);
        logger.info(cDate);
        try {
            pw = SecurityStringUtil.encryptMD5("123123");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setId("accountDtoTest@naver.com");
        accountDto.setName("테스트 이름");
        accountDto.setCDate(cDate);
        accountDto.setPw(pw);


        CalendarDto calendarDto = CalendarDto.builder()
                .title("1")
                .cDate(DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .accountId(accountDto.toEntity().getId())
                .description("테스트 설명")
                .build();

        //when
        Calendar calendar = calendarDto.toEntity();
        //then
        Assert.assertEquals(calendarDto.getDescription(), calendar.getDescription());
        Assert.assertEquals(calendarDto.getTitle(), calendar.getTitle());
        Assert.assertEquals(calendarDto.getColor(), calendar.getColor());
        Assert.assertEquals(calendarDto.getId(), calendar.getId());
    }

    @Test
    public void CalendarDto_연쇄_컨버팅_테스트() {

        //given
        String pw = "";
        String cDate = DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT);
        logger.info(cDate);
        try {
            pw = SecurityStringUtil.encryptMD5("123123");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        AccountDto accountDto = new AccountDto();
        accountDto.setId("accountDtoTest@naver.com");
        accountDto.setName("테스트 이름");
        accountDto.setCDate(cDate);
        accountDto.setPw(pw);


        Calendar calendar = Calendar.builder()
                .title("1")
                .cDate(DateUtil.now())
                .color("#fff")
                .account(accountDto.toEntity())
                .description("테스트 설명")
                .build();

        //when
        Calendar reConvertCalendar = new CalendarDto().of(calendar).toEntity();

        //then
        Assert.assertEquals(reConvertCalendar.getDescription(), calendar.getDescription());
        Assert.assertEquals(reConvertCalendar.getTitle(), calendar.getTitle());
        Assert.assertEquals(reConvertCalendar.getColor(), calendar.getColor());
        Assert.assertEquals(reConvertCalendar.getId(), calendar.getId());
        Assert.assertEquals(reConvertCalendar.getId(), calendar.getId());
    }
}
