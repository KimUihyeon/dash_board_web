package com.sutdy.dashboard.domain.calendars;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sutdy.dashboard.domain.todo.QTodo;
import com.sutdy.dashboard.domain.todo.Todo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;


import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

/**
 * @author kuh
 * @since 2020.08.12
 */
@Repository
public class CalendarRepositoryImpl extends QuerydslRepositorySupport implements CalendarCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public CalendarRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Calendar.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public void deleteCalendar(Long id) {
        this.jpaQueryFactory.delete(QEvent.event)
                .where(QEvent.event.calendar.id.eq(id))
                .execute();

        this.jpaQueryFactory
                .delete(QCalendar.calendar)
                .where(QCalendar.calendar.id.eq(id))
                .execute();
    }

    @Override
    public List<Calendar> calendarsFindByUserId(String userId) {
        Map<Calendar, List<Event>> transform = this.jpaQueryFactory.selectFrom(QCalendar.calendar)
                .leftJoin(QCalendar.calendar.events, QEvent.event)
                .transform(groupBy(QCalendar.calendar).as(list(QEvent.event)));

        return transform.entrySet().stream().map(entity -> entity.getKey()).collect(Collectors.toList());
    }

    @Override
    public List<Calendar> calendarFindByIds(Long[] ids) {
        List<Event> events = this.jpaQueryFactory.selectFrom(QEvent.event)
                .rightJoin(QEvent.event.calendar, QCalendar.calendar)
                .where(QCalendar.calendar.id.in(ids))
                .orderBy(QCalendar.calendar.id.desc())
                .fetch();
        Set<Calendar> cals = new HashSet<>();

        for (Event event : events) {
            Calendar currentCal = event.getCalendar();
            if (!cals.contains(currentCal)) {
                cals.add(currentCal);
            }
            currentCal.getEvents().add(event);
            event.setCalendar(null);
        }
        return new ArrayList<>(cals);
    }

    @Override
    public List<Calendar> calendarFindByIdsWhereDateRange(Long[] ids, Timestamp startDate, Timestamp endDate) {

        List<Event> events = this.jpaQueryFactory.selectFrom(QEvent.event)
                .rightJoin(QEvent.event.calendar, QCalendar.calendar)
                .where(QCalendar.calendar.id.in(ids))
                .orderBy(QCalendar.calendar.id.desc())
                .fetch();

        Set<Calendar> cals = new HashSet<>();
        for (Event event : events) {
            Calendar currentCal = event.getCalendar();
            if (!cals.contains(currentCal)) {
                cals.add(currentCal);
            }
            currentCal.getEvents().add(event);
            event.setCalendar(null);
        }
        return new ArrayList<>(cals);
    }
}