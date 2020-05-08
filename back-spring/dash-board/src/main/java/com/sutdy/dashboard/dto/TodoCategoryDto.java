package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import com.sutdy.dashboard.setting.util.AppConfig;
import com.sutdy.dashboard.setting.util.Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author kuh
 * @since 2020.05.06
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCategoryDto extends AbsDtoConverter<TodoCategory> {

    private Long id;

    private String title;

    private String icon = "el-icon-folder";

    private boolean canModify;

    private String iconColor = "white";

    private String fontColor = "white";

    private String cDate;


    public TodoCategoryDto(TodoCategory entity){
        createDto(entity);
    }


    @Override
    public TodoCategory toEntity() {
        return TodoCategory.builder()
                .id(this.id)
                .title(this.title)
                .icon(this.icon)
                .canModify(this.canModify)
                .cDate(Util.stringToLocalDateTime(this.cDate, AppConfig.DATE_FORMAT))
                .iconColor(this.iconColor)
                .fontColor(this.fontColor)
                .build();
    }

    @Override
    public void createDto(TodoCategory entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.canModify = entity.isCanModify();
        this.cDate = entity.getCDate() == null ? null : Util.localDateTimeToString(entity.getCDate() , AppConfig.DATE_FORMAT);
        this.icon = entity.getIcon();
        this.iconColor = entity.getIconColor();
        this.fontColor = entity.getFontColor();
    }
}
