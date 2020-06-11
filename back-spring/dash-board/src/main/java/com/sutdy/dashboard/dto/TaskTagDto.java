package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.calendars.Task;
import com.sutdy.dashboard.domain.calendars.TaskTag;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author kuh
 * @since 2020.06.11
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTagDto extends AbsDtoConverter<TaskTag> {

    private Long id;
    private String title;
    private String description;
    private String cDate;
    private String color; // HexColor 예)#fff

    private String userId;


    public TaskTagDto(TaskTag entity) {
        this.createDto(entity);
    }


    @Override
    public TaskTag toEntity() {
        return TaskTag.builder()
                .color(this.color)
                .cDate(DateUtil.stringToLocalDateTime(this.cDate , ApplicationStringConfig.DATE_FORMAT))
                .description(this.description)
                .title(this.title)
                .build();

    }

    @Override
    public void createDto(TaskTag entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.color = entity.getColor();
        this.cDate = DateUtil.localDateTimeToString(entity.getCDate() , ApplicationStringConfig.DATE_FORMAT);
        this.userId = entity.getAccount() != null ? entity.getAccount().getId() : null;
    }
}
