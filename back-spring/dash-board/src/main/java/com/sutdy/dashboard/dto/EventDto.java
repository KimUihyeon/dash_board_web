package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Event;
import com.sutdy.dashboard.dto.convert.interfacies.ToConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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


    @Override
    public Event toEntity() {
        return Event.builder()
                .id(this.id)
                .title(this.title)
                .icon(this.icon)
                .context(this.context)
                .cDate(DateUtil.stringToLocalDateTime(this.cDate , ApplicationStringConfig.DATE_FORMAT))
                .uDate(DateUtil.stringToLocalDateTime(this.uDate , ApplicationStringConfig.DATE_FORMAT))
                .sDate(DateUtil.stringToLocalDateTime(this.sDate , ApplicationStringConfig.DATE_FORMAT))
                .eDate(DateUtil.stringToLocalDateTime(this.eDate , ApplicationStringConfig.DATE_FORMAT))
                .build();
    }

    @Override
    public EventDto of(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .icon(event.getTitle())
                .context(event.getTitle())
                .cDate(DateUtil.localDateTimeToString(event.getCDate() , ApplicationStringConfig.DATE_FORMAT))
                .uDate(DateUtil.localDateTimeToString(event.getUDate() , ApplicationStringConfig.DATE_FORMAT))
                .sDate(DateUtil.localDateTimeToString(event.getSDate() , ApplicationStringConfig.DATE_FORMAT))
                .eDate(DateUtil.localDateTimeToString(event.getEDate() , ApplicationStringConfig.DATE_FORMAT))
                .build();
    }
}
