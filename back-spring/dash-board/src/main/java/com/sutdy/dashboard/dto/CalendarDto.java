package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Calendar;
import com.sutdy.dashboard.dto.convert.interfacies.ToConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDto implements ToConverter<Calendar, CalendarDto> {

    private Long id;

    private String title;
    private String description;
    private String color; // HexColor 예)#fff
    private String cDate;

    private String accountId;
    private List<EventDto> events;


    @Override
    public Calendar toEntity() {
        return Calendar.builder()
                .id(this.id)
                .title(this.title)
                .description(this.description)
                .color(this.color)
                .cDate(DateUtil.toTimeStamp(this.cDate, ApplicationStringConfig.DATE_FORMAT))
                .build();
    }

    @Override
    public CalendarDto of(Calendar calendar) {
//        CalendarDto dto =  CalendarDto.builder()
//                .id(calendar.getId())
//                .title(calendar.getTitle())
//                .description(calendar.getDescription())
//                .color(calendar.getColor())
//                .cDate(DateUtil.TimestampToString(calendar.getCDate(), ApplicationStringConfig.DATE_FORMAT))
//                .build();
        CalendarDto dto = ModelConverter.map(calendar, CalendarDto.class);
        dto.setCDate(DateUtil.toString(calendar.getCDate(), ApplicationStringConfig.DATE_FORMAT));
        return dto;
//        return dto;
    }
}
