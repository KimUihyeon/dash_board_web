package com.sutdy.dashboard.domain.calendars;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kuh
 * @since 2020.06.11
 */
public interface CalendarRepository extends JpaRepository<Calendar, Long> , CalendarCustomRepository {
}
