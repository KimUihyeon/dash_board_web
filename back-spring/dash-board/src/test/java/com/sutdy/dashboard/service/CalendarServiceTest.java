package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.calendars.Calendar;
import com.sutdy.dashboard.domain.calendars.CalendarRepository;
import com.sutdy.dashboard.domain.calendars.Event;
import com.sutdy.dashboard.domain.calendars.EventRepository;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.dto.CalendarDto;
import com.sutdy.dashboard.dto.CalendarDto;
import com.sutdy.dashboard.dto.EventDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.06.11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CalendarServiceTest {

    private Logger logger = LoggerFactory.getLogger(CalendarServiceTest.class);

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private EventRepository eventRepository;

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
                    .cDate(DateUtil.now())
                    .build());
            dto.setPw("123123123");

            accountDto = this.accountService.save(dto);
        }

        CalendarDto dto = CalendarDto.builder()
                .accountId(accountDto.getId())
                .cDate(DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
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
    public void calendar_event_리스트_불러오기_테스트() {

        //given
        short calCount = 3;
        short eventCount = 7;

        List<CalendarDto> originCalendars = new ArrayList();
        List<Long> savedEvents = new ArrayList<>();

        for (int j = 0; j < calCount; j++) {
            CalendarDto cal = CalendarDto.builder()
                    .title(j + "title 테스트")
                    .color("#f0f")
                    .description("테스트 설명")
                    .cDate(DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
                    .build();

            Calendar savedCalendar = this.calendarRepository.save(cal.toEntity());
            CalendarDto savedCalendarDto = new CalendarDto().of(savedCalendar);
            savedCalendarDto.setEvents(new ArrayList<>());


            for (int i = 0; i < eventCount; i++) {
                Event creatEvent = Event.builder()
                        .title(j + "" + i + " 테스트 이벤트")
                        .cDate(DateUtil.now())
                        .calendar(savedCalendar)
                        .build();
                this.eventRepository.save(creatEvent);
                savedCalendarDto.getEvents().add(new EventDto().of(creatEvent));
            }
            savedEvents.add(savedCalendar.getId());
            originCalendars.add(savedCalendarDto);
        }
        Collections.reverse(originCalendars);
        for (CalendarDto cal : originCalendars) {
        }


        //whene
        List<CalendarDto> calendars = this.calendarService.calendarFindByIds(savedEvents.toArray(new Long[savedEvents.size()]));


        //then
        for (int i = 0; i < calCount; i++) {
            CalendarDto originCal = originCalendars.get(i);
            CalendarDto targetCal = calendars.get(i);

            for (int j = 0; j < eventCount; j++) {
                logger.info("i -> " + i + "\t j -> " + j);
                EventDto originEvent = originCalendars.get(i).getEvents().get(j);
                EventDto targetEvent = calendars.get(i).getEvents().get(j);

                Assert.assertEquals(originEvent.getId(), targetEvent.getId());
                Assert.assertEquals(originEvent.getIcon(), targetEvent.getIcon());
                Assert.assertEquals(originEvent.getTitle(), targetEvent.getTitle());
                Assert.assertEquals(originEvent.getCDate(), targetEvent.getCDate());
                Assert.assertEquals(originEvent.getEDate(), targetEvent.getEDate());
                Assert.assertEquals(originEvent.getSDate(), targetEvent.getSDate());
                Assert.assertEquals(originEvent.getUDate(), targetEvent.getUDate());
                Assert.assertEquals(originEvent.getTitle(), targetEvent.getTitle());
                Assert.assertEquals(originEvent.getContext(), targetEvent.getContext());
            }

            Assert.assertEquals(originCal.getId(), targetCal.getId());
            Assert.assertEquals(originCal.getTitle(), targetCal.getTitle());
            Assert.assertEquals(originCal.getColor(), targetCal.getColor());
            Assert.assertEquals(originCal.getAccountId(), targetCal.getAccountId());
            Assert.assertEquals(originCal.getDescription(), targetCal.getDescription());
        }
    }

    @Test
    @Transactional
    public void calendar_event_범위_리스트_불러오기_테스트() {

        //given
        short calCount = 3;
        short eventCount = 7;

        List<CalendarDto> originCalendars = new ArrayList();
        List<Long> savedEvents = new ArrayList<>();

        for (int j = 0; j < calCount; j++) {
            CalendarDto cal = CalendarDto.builder()
                    .title(j + "title 테스트")
                    .color("#f0f")
                    .description("테스트 설명")
                    .cDate(DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
                    .build();

            Calendar savedCalendar = this.calendarRepository.save(cal.toEntity());
            CalendarDto savedCalendarDto = new CalendarDto().of(savedCalendar);
            savedCalendarDto.setEvents(new ArrayList<>());


            for (int i = 0; i < eventCount; i++) {
                LocalDateTime cDate = null;
                if (i % 2 == 0) {
                    cDate = DateUtil.now();
                }
                Event creatEvent = Event.builder()
                        .title(j + "" + i + " 테스트 이벤트")
                        .cDate(cDate)
                        .calendar(savedCalendar)
                        .build();
                this.eventRepository.save(creatEvent);
                if (cDate != null) {
                    savedCalendarDto.getEvents().add(new EventDto().of(creatEvent));
                }
            }
            savedEvents.add(savedCalendar.getId());
            originCalendars.add(savedCalendarDto);
        }
        Collections.reverse(originCalendars);

        String year = String.valueOf(DateUtil.now().getYear());
        String month = String.valueOf(DateUtil.now().getMonthValue());


        //whene
        List<CalendarDto> calendars = this.calendarService.calendarFindByIdsWhereMonth(savedEvents.toArray(new Long[savedEvents.size()]), year, month);
        Collections.sort(calendars, (o1, o2) ->
                o2.getId().compareTo(o1.getId())
        );


        //then
        for (int i = 0; i < calCount; i++) {
            CalendarDto originCal = originCalendars.get(i);
            CalendarDto targetCal = calendars.get(i);

            for (int j = 0; j < originCal.getEvents().size(); j++) {
                logger.info("i -> " + i + "\t j -> " + j);
                EventDto originEvent = originCalendars.get(i).getEvents().get(j);
                EventDto targetEvent = calendars.get(i).getEvents().get(j);

                Assert.assertEquals(originEvent.getId(), targetEvent.getId());
                Assert.assertEquals(originEvent.getIcon(), targetEvent.getIcon());
                Assert.assertEquals(originEvent.getTitle(), targetEvent.getTitle());
                Assert.assertEquals(originEvent.getCDate(), targetEvent.getCDate());
                Assert.assertEquals(originEvent.getEDate(), targetEvent.getEDate());
                Assert.assertEquals(originEvent.getSDate(), targetEvent.getSDate());
                Assert.assertEquals(originEvent.getUDate(), targetEvent.getUDate());
                Assert.assertEquals(originEvent.getTitle(), targetEvent.getTitle());
                Assert.assertEquals(originEvent.getContext(), targetEvent.getContext());
            }

            Assert.assertEquals(originCal.getId(), targetCal.getId());
            Assert.assertEquals(originCal.getTitle(), targetCal.getTitle());
            Assert.assertEquals(originCal.getColor(), targetCal.getColor());
            Assert.assertEquals(originCal.getAccountId(), targetCal.getAccountId());
            Assert.assertEquals(originCal.getDescription(), targetCal.getDescription());
        }
    }


    @Test
    @Transactional
    public void calendar_저장_테스트() throws NoSuchAlgorithmException {
        //given
        Account account = this.accountService.findById(loginId).toEntity();
        CalendarDto dto = CalendarDto.builder()
                .accountId(account.getId())
                .cDate(DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
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
                    .cDate(DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
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
                .cDate(DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
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
                .cDate(DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
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
