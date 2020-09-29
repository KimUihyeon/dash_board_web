package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.calendars.Calendar;
import com.sutdy.dashboard.domain.calendars.CalendarRepository;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.dto.CalendarDto;
import com.sutdy.dashboard.dto.CalendarDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.06.11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CalendarServiceTest {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private AccountService accountService;


    private String loginId = "smaple_account1@naver.com";
    private Long tempCalendarId;


    @Before
    public void createSampleData() throws NoSuchAlgorithmException {

        AccountDto accountDto = null;

        if (this.accountService.findById(loginId) != null) {
            accountDto = this.accountService.findById(loginId);
        } else {

            AccountDto dto = new AccountDto().of(Account.builder()
                    .id(loginId)
                    .name("userName")
                    .cDate(LocalDateTime.now())
                    .build());
            dto.setPw("123123123");

            accountDto = this.accountService.save(dto);
        }

        CalendarDto dto = CalendarDto.builder()
                .accountId(accountDto.getId())
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .description("test Data")
                .title("테스트 캘린더 테그 생성")
                .build();

        this.tempCalendarId = this.calendarService.save(dto).getId();
    }

    @After
    public void cleanup() {
        this.accountService.delete(loginId);
        this.calendarRepository.deleteById(tempCalendarId);
    }


    @Test
    @Transactional
    public void calendar_저장_테스트() throws NoSuchAlgorithmException {
        //given
        Account account = this.accountService.findById(loginId).toEntity();
        CalendarDto dto = CalendarDto.builder()
                .accountId(account.getId())
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .description("test Data")
                .title("테스트 캘린더 테그 생성")
                .build();

        //when
        CalendarDto savedTag = this.calendarService.save(dto);

        //then
        Assert.assertEquals(savedTag.getTitle(), dto.getTitle());

    }


    @Test
    @Transactional
    public void calendar_리스트_불러오기_테스트() throws NoSuchAlgorithmException {
        //given
        Account account = this.accountService.findById(loginId).toEntity();
        List<CalendarDto> calendars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            CalendarDto dto = CalendarDto.builder()
                    .accountId(account.getId())
                    .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                    .color("#fff")
                    .description("test Data " + i)
                    .title("테스트 캘린더 테그 생성 " + i)
                    .build();
            CalendarDto savedTag = this.calendarService.save(dto);
            calendars.add(savedTag);
        }

        List<Long> ids = calendars.stream().map(t -> t.getId()).collect(Collectors.toList());

        //when
        List<CalendarDto> findAll = this.calendarService.findAllById(ids);

        //then
        Assert.assertNotNull(findAll);
        Assert.assertEquals(findAll.size(), calendars.size());

        Assert.assertEquals(
                findAll.stream()
                        .filter(t -> t.getId() == ids.get(0)).collect(Collectors.toList())
                        .get(0)
                        .getTitle(),
                calendars.stream()
                        .filter(t -> t.getId() == ids.get(0)).collect(Collectors.toList())
                        .get(0)
                        .getTitle()
        );


        Assert.assertEquals(
                findAll.stream()
                        .filter(t -> t.getId() == ids.get(ids.size() - 1)).collect(Collectors.toList())
                        .get(0)
                        .getTitle(),
                calendars.stream()
                        .filter(t -> t.getId() == ids.get(ids.size() - 1)).collect(Collectors.toList())
                        .get(0)
                        .getTitle()
        );


    }

    @Test
    @Transactional
    public void calendar_삭제_테스트() throws NoSuchAlgorithmException {
        //given
        Account account = this.accountService.findById(loginId).toEntity();
        CalendarDto dto = CalendarDto.builder()
                .accountId(account.getId())
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .description("test Data")
                .title("테스트 캘린더 생성")
                .build();
        CalendarDto savedTag = this.calendarService.save(dto);
        CalendarDto deletedTag = this.calendarService.delete(savedTag.getId());

        //when
        Calendar findTag = this.calendarRepository.findById(deletedTag.getId()).orElse(null);


        //then
        Assert.assertEquals(savedTag.getTitle(), dto.getTitle());
        Assert.assertNull(findTag);

    }

    @Test
    @Transactional
    public void calendar_수정_테스트() throws NoSuchAlgorithmException {
        // given
        Account account = this.accountService.findById(loginId).toEntity();
        CalendarDto dto = CalendarDto.builder()
                .accountId(account.getId())
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .description("test Data")
                .title("테스트 캘린더 생성")
                .build();
        CalendarDto savedTag = this.calendarService.save(dto);
        String changeTitle = "변경된 타이틀 !";
        savedTag.setTitle(changeTitle);

        //when
        CalendarDto updatedDto = this.calendarService.update(savedTag.getId(), savedTag);

        //then
        Assert.assertEquals(changeTitle, updatedDto.getTitle());
        Assert.assertEquals(updatedDto.getTitle(), savedTag.getTitle());
    }
}
