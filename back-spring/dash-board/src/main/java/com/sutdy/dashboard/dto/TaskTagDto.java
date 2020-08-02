package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Task;
import com.sutdy.dashboard.domain.calendars.TaskTag;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import com.sutdy.dashboard.dto.common.ToEntity;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.PropertyMap;

import java.util.List;

/**
 * @author kuh
 * @since 2020.06.11
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTagDto implements ToEntity<TaskTag> {

    private Long id;
    private String title;
    private String description;
    private String cDate;
    private String color; // HexColor 예)#fff

    private String userId;


    @Override
    public TaskTag toEntity() {
        return TaskTag.builder()
                .color(this.color)
                .cDate(DateUtil.stringToLocalDateTime(this.cDate, ApplicationStringConfig.DATE_FORMAT))
                .description(this.description)
                .title(this.title)
                .build();

    }

    public TaskTagDto(TaskTag entity){
        of(entity);
    }

    @Override
    public void of(TaskTag taskTag) {
        PropertyMap<TaskTag, TaskTagDto> map = new PropertyMap<TaskTag, TaskTagDto>() {
            @Override
            protected void configure() {

            }
        };

        ModelConverter.map(map, taskTag, TaskTagDto.class);
    }
}
