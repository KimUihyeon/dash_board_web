package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Task;
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
public class TaskDto implements ToConverter<Task, TaskDto> {

    private Long id;

    private String title;
    private String description;
    private String color; // HexColor 예)#fff
    private LocalDateTime cDate;

    public TaskDto(Task entity){
        of(entity);
    }

    @Override
    public Task toEntity() {
        return Task.builder()
                .cDate(cDate)
                .context(description)
                .title(title)
                .build();
    }

    @Override
    public TaskDto of(Task task) {
        PropertyMap<Task, TaskDto> map = new PropertyMap<Task, TaskDto>() {
            @Override
            protected void configure() {

            }
        };

        return ModelConverter.map(map, task, TaskDto.class);
    }

}
