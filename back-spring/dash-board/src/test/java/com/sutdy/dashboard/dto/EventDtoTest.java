package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Calendar;
import com.sutdy.dashboard.domain.calendars.Event;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * dto - entity
 * modelMapper Test
 * @author kuh
 * @since 08.20.03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EventDtoTest {

    @Test
    public void EventDto_엔티티_컨버팅_테스트(){
        //given
        EventDto dto = EventDto.builder()
                .icon("icon")
                .context("테스트 내용")
                .cDate(DateUtil.toString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
                .title("Test title")
                .build();

        //when
        Event event = dto.toEntity();

        //then
        Assert.assertEquals(dto.getContext(), event.getContext());
        Assert.assertEquals(dto.getIcon(), event.getIcon());
        Assert.assertEquals(dto.getTitle(), event.getTitle());
        Assert.assertEquals(dto.getCDate(), DateUtil.toString(event.getCDate() , ApplicationStringConfig.DATE_FORMAT));
        Assert.assertEquals(dto.getId(), event.getId());
    }

    @Test
    public void EventDto_컨버팅_테스트(){
        //given
        Calendar c =  Calendar.builder().id( new Long(9991)).build();

        Event event = Event.builder()
                .icon("icon")
                .context("테스트 내용")
                .cDate(DateUtil.now())
                .title("Test title")
                .calendar(c)
                .build();

        //when
        EventDto dto = new EventDto().of(event);
        if(event.getCalendar() != null ){
            dto.setCalendarId(event.getCalendar().getId());
        }

        //then
        Assert.assertEquals(dto.getCDate(), DateUtil.toString(event.getCDate() , ApplicationStringConfig.DATE_FORMAT));
        Assert.assertEquals(dto.getContext(), event.getContext());
        Assert.assertEquals(dto.getIcon(), event.getIcon());
        Assert.assertEquals(dto.getTitle(), event.getTitle());
        Assert.assertEquals(dto.getId(), event.getId());
        Assert.assertEquals(dto.getCalendarId(), event.getCalendar().getId());
    }

    @Test
    public void EventDto_연쇄_컨버팅_테스트() {
        //given

        Event event = Event.builder()
                .icon("icon")
                .context("테스트 내용")
                .cDate(DateUtil.now())
                .title("Test title")
                .build();

        //when
        Event reconvertEvent = new EventDto().of(event).toEntity();

        //then
        Assert.assertEquals(DateUtil.toString(reconvertEvent.getCDate(), ApplicationStringConfig.DATE_FORMAT)
                , DateUtil.toString(event.getCDate() , ApplicationStringConfig.DATE_FORMAT));
        Assert.assertEquals(reconvertEvent.getContext(), event.getContext());
        Assert.assertEquals(reconvertEvent.getIcon(), event.getIcon());
        Assert.assertEquals(reconvertEvent.getTitle(), event.getTitle());
        Assert.assertEquals(reconvertEvent.getId(), event.getId());

    }
}
