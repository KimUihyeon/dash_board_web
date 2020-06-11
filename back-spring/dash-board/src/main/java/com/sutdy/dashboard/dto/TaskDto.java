package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Task;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.06.11
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends AbsDtoConverter<Task> {

    private Long id;

    private String title;
    private String description;
    private String color; // HexColor 예)#fff
    private LocalDateTime cDate;

    public TaskDto(Task entity) {
        this.createDto(entity);
    }

    @Override
    public Task toEntity() {
        return null;
    }

    @Override
    public void createDto(Task entity) {

    }
}
