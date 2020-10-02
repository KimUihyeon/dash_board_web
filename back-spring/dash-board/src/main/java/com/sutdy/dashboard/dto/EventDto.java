package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Event;
import com.sutdy.dashboard.dto.convert.interfacies.ToConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto implements ToConverter<Event, EventDto> {

    private Long id;

    private String title;
    private String icon;
    private String context;

    private String cDate; // 생성
    private String uDate; // 업데이트
    private String sDate; // 시작일
    private String eDate; // 종료일

    private Long calendarId;


    @Override
    public Event toEntity() {
        return Event.builder()
                .id(this.id)
                .title(this.title)
                .icon(this.icon)
                .context(this.context)
                .cDate(DateUtil.toTimeStamp(this.cDate, ApplicationStringConfig.DATE_FORMAT))
                .uDate(DateUtil.toTimeStamp(this.uDate, ApplicationStringConfig.DATE_FORMAT))
                .sDate(DateUtil.toTimeStamp(this.sDate, ApplicationStringConfig.DATE_FORMAT))
                .eDate(DateUtil.toTimeStamp(this.eDate, ApplicationStringConfig.DATE_FORMAT))
                .build();
    }

    @Override
    public EventDto of(Event event) {
        EventDto eventDto = ModelConverter.map(event, EventDto.class);
        eventDto.setCDate(DateUtil.toString(event.getCDate(), ApplicationStringConfig.DATE_FORMAT));
        eventDto.setUDate(DateUtil.toString(event.getUDate(), ApplicationStringConfig.DATE_FORMAT));
        eventDto.setSDate(DateUtil.toString(event.getSDate(), ApplicationStringConfig.DATE_FORMAT));
        eventDto.setEDate(DateUtil.toString(event.getEDate(), ApplicationStringConfig.DATE_FORMAT));
        return eventDto;

    }
}
