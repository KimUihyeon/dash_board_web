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

//        이전 코드 ..!
//        List<Calendar> cals = this.jpaQueryFactory.selectFrom(QCalendar.calendar)
//                .where(QCalendar.calendar.account.id.eq(userId))
//                .fetch();
//        return cals;

//
//        aaa.stream().map(t-> {
//
//            List<Event> aaaaa = t.getEvent();
//            if(t.getEvent() == null){
//
//            }
//
//            return t;
//        });

//
//        List<Event> events = this.jpaQueryFactory.selectFrom(QEvent.event)
//                .rightJoin(QEvent.event.calendar, QCalendar.calendar)
//                .where(QCalendar.calendar.account.id.eq(userId))
//                .orderBy(QCalendar.calendar.id.desc())
//                .fetch();
//        Set<Calendar> cals = new HashSet<>();
//
//        for (Event event : events) {
//            Calendar currentCal = event.getCalendar();
//            if (!cals.contains(currentCal)) {
//                cals.add(currentCal);
//                currentCal.setEvent(new ArrayList());
//            }
//            currentCal.getEvent().add(event);
//            event.setCalendar(null);
//        }
//        return new ArrayList<>(cals);

//
//        return this.jpaQueryFactory.selectFrom(QCalendar.calendar)
//                .where(QCalendar.calendar.account.id.eq(userId))
//                .fetch();


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
            }
            currentCal.getEvents().add(event);
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
    public List<Calendar> calendarFindByIdsWhereDateRange(Long[] ids, Timestamp startDate, Timestamp endDate) {
        // TODO User아이디로 앞 단에서 못들어오게 체크할것.

        List<Event> events = this.jpaQueryFactory.selectFrom(QEvent.event)
                .rightJoin(QEvent.event.calendar, QCalendar.calendar)
                .where(QCalendar.calendar.id.in(ids))
//                        .and(
//
//                                //TODO :  내일 여기부터
//                                QEvent.event.sDate.goe(startDate).and(QEvent.event.sDate.loe(endDate.withNano(0)))
//                                        .or(QEvent.event.eDate.goe(startDate.withNano(0)).and(QEvent.event.eDate.loe(endDate.withNano(0)))
//                                        )))
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