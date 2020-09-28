package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Event;
import com.sutdy.dashboard.dto.convert.interfacies.ToConverter;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.PropertyMap;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.06.11
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto implements ToConverter<Event, TaskDto> {

    private Long id;

    private String title;
    private String description;
//    private String color; // HexColor 예)#fff
    private LocalDateTime cDate;

    public TaskDto(Event entity){
        of(entity);
    }

    @Override
    public Event toEntity() {
        return Event.builder()
                .cDate(cDate)
                .context(description)
                .title(title)
                .build();
    }

    @Override
    public TaskDto of(Event task) {
        PropertyMap<Event, TaskDto> map = new PropertyMap<Event, TaskDto>() {
            @Override
            protected void configure() {

            }
        };

        return ModelConverter.map(map, task, TaskDto.class);
    }

}
