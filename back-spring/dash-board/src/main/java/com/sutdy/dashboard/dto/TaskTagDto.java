package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Calendar;
import com.sutdy.dashboard.dto.convert.interfacies.ToConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.PropertyMap;

/**
 * @author kuh
 * @since 2020.06.11
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTagDto implements ToConverter<Calendar, TaskTagDto> {

    private Long id;
    private String title;
    private String description;
    private String cDate;
    private String color; // HexColor 예)#fff

    private String userId;


    @Override
    public Calendar toEntity() {
        return Calendar.builder()
                .color(this.color)
                .cDate(DateUtil.stringToLocalDateTime(this.cDate, ApplicationStringConfig.DATE_FORMAT))
                .description(this.description)
                .title(this.title)
                .build();

    }

    public TaskTagDto(Calendar entity){
        of(entity);
    }

    @Override
    public TaskTagDto of(Calendar calendar) {
        PropertyMap<Calendar, TaskTagDto> map = new PropertyMap<Calendar, TaskTagDto>() {
            @Override
            protected void configure() {

            }
        };

        return ModelConverter.map(map, calendar, TaskTagDto.class);
    }
}
