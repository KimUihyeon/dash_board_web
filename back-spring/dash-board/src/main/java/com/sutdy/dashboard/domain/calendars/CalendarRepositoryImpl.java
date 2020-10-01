package com.sutdy.dashboard.domain.calendars;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sutdy.dashboard.domain.todo.QTodo;
import com.sutdy.dashboard.domain.todo.Todo;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

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
    public List<Calendar> calendarFindByIds(Long[] ids) {
        // TODO User아이디로 앞 단에서 못들어오게 체크할것.

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
                currentCal.setEvent(new ArrayList());
            }
            currentCal.getEvent().add(event);
            event.setCalendar(null);
        }
        return new ArrayList<>(cals);

        /**
         * TODO : # queryDsl 기술문제 Left outer join 20.09.30
         * Calendar - evnet 1:M leftOuter join 해서
         * Calendar 자식으로 List<event>에 담고싶은데 안담겨짐 ..
         *
         * event.calendar is not a root path 애러나옴
         *
         * 해당쿼리 ..
         *
         *
         return this.jpaQueryFactory.selectFrom(QEvent.event.calendar)
         .leftJoin(QEvent.event.calendar , QCalendar.calendar)
         .where(QEvent.event.calendar.id.in(ids))
         .orderBy(QEvent.event.calendar.id.desc())
         .fetch();
         *
         */
    }

    @Override
    public List<Calendar> calendarFindByIdsWhereDateRange(Long[] ids, LocalDateTime startDate, LocalDateTime endDate) {
        // TODO User아이디로 앞 단에서 못들어오게 체크할것.

        List<Event> events = this.jpaQueryFactory.selectFrom(QEvent.event)
                .rightJoin(QEvent.event.calendar, QCalendar.calendar)
                .where(QCalendar.calendar.id.in(ids).and(QEvent.event.cDate.goe(startDate)).and(QEvent.event.cDate.loe(endDate)))
                .orderBy(QCalendar.calendar.id.desc())
                .fetch();

        Set<Calendar> cals = new HashSet<>();
        for (Event event : events) {
            Calendar currentCal = event.getCalendar();
            if (!cals.contains(currentCal)) {
                cals.add(currentCal);
                currentCal.setEvent(new ArrayList());
            }
            currentCal.getEvent().add(event);
            event.setCalendar(null);
        }
        return new ArrayList<>(cals);
    }
}