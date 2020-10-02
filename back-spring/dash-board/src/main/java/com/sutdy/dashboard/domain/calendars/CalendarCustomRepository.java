package com.sutdy.dashboard.domain.calendars;


import java.sql.Timestamp;
import java.util.List;

/**
 * @author kuh
 * @since 2020.08.12
 */
public interface CalendarCustomRepository {


    public void deleteCalendar(Long id);

    public List<Calendar> calendarsFindByUserId(String userId);

    public List<Calendar> calendarFindByIds(Long[] ids);

    public List<Calendar> calendarFindByIdsWhereDateRange(Long[] ids, Timestamp startDate, Timestamp endDate);
}