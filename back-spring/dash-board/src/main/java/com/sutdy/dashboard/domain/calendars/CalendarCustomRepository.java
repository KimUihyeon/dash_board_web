package com.sutdy.dashboard.domain.calendars;

import java.util.List;

/**
 * @author kuh
 * @since 2020.08.12
 */
public interface CalendarCustomRepository {

    public List<Calendar> calendarFindByIds(Long[] ids);
}