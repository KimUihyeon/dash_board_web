package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private String icon;

    private boolean canModify;

    private String iconColor;

    private String fontColor;

    private LocalDateTime cDate;

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
                .cDate(this.cDate)
                .iconColor(this.iconColor)
                .fontColor(this.fontColor)
                .build();
    }

    @Override
    public void createDto(TodoCategory entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.canModify = entity.isCanModify();
        this.cDate = entity.getCDate();
        this.icon = entity.getIcon();
        this.iconColor = entity.getIconColor();
        this.fontColor = entity.getFontColor();
    }
}
