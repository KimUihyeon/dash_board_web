package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.calendars.Calendar;
import com.sutdy.dashboard.domain.calendars.Event;
import com.sutdy.dashboard.domain.calendars.EventRepository;
import com.sutdy.dashboard.domain.calendars.CalendarRepository;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.members.AccountRepository;
import com.sutdy.dashboard.dto.EventDto;
import com.sutdy.dashboard.dto.CalendarDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.AccessException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.06.11
 */
@Service("calendarService")
public class CalendarService extends BaseCrudService<Calendar, CalendarDto, Long> {


    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    private EventRepository eventRepository;
    private CalendarRepository calendarRepository;
    private AccountRepository accountRepository;

    @Autowired
    public CalendarService(EventRepository eventRepository,
                           AccountRepository accountRepository,
                           CalendarRepository calendarRepository) {
        super(calendarRepository);
        this.eventRepository = eventRepository;
        this.accountRepository = accountRepository;
        this.calendarRepository = calendarRepository;
    }

    @Override
    @Transactional
    public CalendarDto update(Long pk, CalendarDto dto) {
        Calendar entity = this.findEntityById(pk);
        entity.patch(dto);
        return new CalendarDto().of(entity);
    }

    @Override
    @Transactional
    public CalendarDto delete(Long pk) {
        CalendarDto calendarDto = this.findById(pk);
        this.calendarRepository.deleteCalendar(pk);
        return calendarDto;
    }


    @Override
    @Transactional
    @Deprecated
    public CalendarDto save(CalendarDto dto) throws UnsupportedOperationException {
        // ## note : 보안문제로 미사용

        Calendar entity = dto.toEntity();
        Account account = this.accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));

//        if(!dto.getAccountId().equals(userId)){ ## 잠시 막아둠
//            throw new AccessException("잘못된 접근 입니다.");
//        }

        entity.setAccount(account);
        return new CalendarDto().of(this.calendarRepository.save(entity));

        //throw new UnsupportedOperationException("");
    }

    /**
     *
     * @param dto request로 들어온 객체
     * @param userId 필터를 타고 JWT로 생성된 userId
     * @return
     * @throws AccessException 권한처리
     */
    @Transactional
    public CalendarDto save(CalendarDto dto, String userId) throws AccessException {
        Calendar entity = dto.toEntity();

//        if(!dto.getAccountId().equals(userId)){ ## 잠시 막아둠
//            throw new AccessException("잘못된 접근 입니다.");
//        }

        Account account = this.accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));

        entity.setAccount(account);
        return new CalendarDto().of(this.calendarRepository.save(entity));
    }

    @Transactional
    public EventDto eventUpdate(EventDto dto) {
        Event origin = this.eventRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));

        if(dto.getCalendarId() != origin.getCalendar().getId()){
            logger.info("외래키 변경 발생");
        }

        origin.patch(dto);
        return new EventDto().of(origin);
    }

    public List<EventDto> eventFindAllById(Iterable<Long> ids) {
        return this.eventRepository.findAllById(ids)
                .stream()
                .map(t -> new EventDto().of(t))
                .collect(Collectors.toList());
    }

    @Transactional
    public EventDto eventDelete(Long pk) {
        EventDto dto = this.eventFindById(pk);
        this.eventRepository.deleteById(pk);
        return dto;
    }


    @Transactional
    public EventDto eventSave(EventDto dto) {
        Event entity = dto.toEntity();
        if (dto.getCalendarId() != null && dto.getCalendarId() > 0) {
            Calendar calendar = this.calendarRepository
                    .findById(dto.getCalendarId())
                    .orElseThrow(() -> new IllegalArgumentException("calendarId 값이 올바르지 않습니다."));

            entity.setCalendar(calendar);
        }
        return new EventDto().of(this.eventRepository.save(entity));
    }

    public EventDto eventFindById(Long id) {
        Event event = this.eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));
                        return new EventDto().of(event);
                    }

                    public List<CalendarDto> calendarFindByIds(Long[] ids) {
                        return this.calendarRepository.calendarFindByIds(ids)
                                .stream()
                                .map(c -> {
                                    CalendarDto dto = new CalendarDto().of(c);

                                    if (c.getEvents() != null && c.getEvents().size() > 0) {
                                        List<EventDto> eventDtos = c.getEvents() // Cast Event List
                                .stream()
                                .map(e -> new EventDto().of(e)).collect(Collectors.toList());
                        dto.setEvents(eventDtos);
                    }
                    return dto;
                }).collect(Collectors.toList());
    }

    public List<CalendarDto> eventsFindByCalendarIdsWhereMonth(Long[] ids, String year, String month) {
        try {
            int yearTemp = Integer.parseInt(year);
            int monthTemp = Integer.parseInt(month);

            Timestamp startDate = DateUtil.firstDayOfMonth(yearTemp, monthTemp, DateUtil.Time.start);
            Timestamp endDate = DateUtil.lastDayOfMonth(yearTemp, monthTemp, DateUtil.Time.end);
            return this.calendarRepository.calendarFindByIdsWhereDateRange(ids, startDate, endDate)
                    .stream()
                    .map(c -> {
                        CalendarDto dto = new CalendarDto().of(c);

                        if (c.getEvents() != null && c.getEvents().size() > 0) {
                            List<EventDto> eventDtos = c.getEvents() // Cast Event List
                                    .stream()
                                    .map(e -> new EventDto().of(e)).collect(Collectors.toList());
                            dto.setEvents(eventDtos);
                        }
                        return dto;
                    }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException("year, month를 정상적으로 입력해주세요");
        }
    }

    public List<CalendarDto> calendarsFindByUserId(String userId) {
        return this.calendarRepository.calendarsFindByUserId(userId)
                .stream()
                .map(c -> {
                    CalendarDto dto = new CalendarDto().of(c);
                    dto.setEvents(new ArrayList<>());

                    // TODO : 임시로직 persistentbag 떨어질떄랑 Null 떨어질때 비교하기.
                    if(c.getEvents() != null) {
                        for(Event event : c.getEvents()){
                            dto.getEvents().add(new EventDto().of(event));
                        }
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    ;
}
