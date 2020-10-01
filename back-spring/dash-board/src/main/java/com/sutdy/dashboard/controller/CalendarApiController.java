package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.dto.CalendarDto;
import com.sutdy.dashboard.dto.EventDto;
import com.sutdy.dashboard.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * CalendarApiController
 *
 * @author Kimuihyeon
 * @since 2020.09.29
 */
@RestController
@RequestMapping("/api/v1/calendar/")
public class CalendarApiController {

    @Autowired
    private CalendarService calendarService;

    // --------------------------------------
    // --------------------------------------

    @GetMapping("/{userId}")
    public List<CalendarDto> getAllCalendarByUserId(@PathVariable String userId) {
        // 모든 서비스단에서 유저관련 권한체크 필요.
        return this.calendarService.calendarsFindByUserId(userId);
    }

    @PostMapping("/")
    public CalendarDto saveCalendar(@RequestBody CalendarDto calendarDto) throws NoSuchAlgorithmException {
        return this.calendarService.save(calendarDto);
    }


    @PatchMapping("/{id}")
    public CalendarDto updateCalendar(@PathVariable Long id, @RequestBody CalendarDto calendarDto) {
        return this.calendarService.update(id, calendarDto);
    }


    @DeleteMapping("/{id}")
    public CalendarDto deleteCalendar(@PathVariable Long id) {
        /**
         * 지울때 해당이벤트도 지우도록 변경
         *  CASCADE 사용 금지.
         */
        return this.deleteCalendar(id);
    }


    // --------------------------------------
    // --------------------------------------

    @GetMapping("/{ids}/events")
    public List<CalendarDto> getEventsByCalendarIds(@PathVariable Long[] ids, String year, String month) {
        return this.calendarService.eventsFindByCalendarIdsWhereMonth(ids, year, month);
    }

    @PostMapping("/event")
    public EventDto saveEvent(@RequestBody EventDto eventDto) {
        return this.calendarService.eventSave(eventDto);
    }

    @PatchMapping("/event")
    public EventDto updateEvent(@RequestBody EventDto eventDto) {
        return  this.calendarService.eventUpdate(eventDto);
    }

    @DeleteMapping("/event/{id}")
    public EventDto deleteEvent(@PathVariable Long id) {
        return  this.calendarService.eventDelete(id);
    }

}
