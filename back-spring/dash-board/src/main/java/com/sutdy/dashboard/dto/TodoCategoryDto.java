package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.TodoCategory;
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
 * @since 2020.05.06
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoCategoryDto implements ToConverter<TodoCategory, TodoCategoryDto> {

    private Long id;

    private String title;

    private String icon = "el-icon-folder";

    private boolean canModify;

    private String iconColor = "white";

    private String fontColor = "white";

    private String cDate;

    private String userId;

    @Override
    public TodoCategory toEntity() {
        return TodoCategory.builder()
                .id(this.id)
                .title(this.title)
                .icon(this.icon)
                .canModify(this.canModify)
                .cDate(DateUtil.stringToLocalDateTime(this.cDate, ApplicationStringConfig.DATE_FORMAT))
                .iconColor(this.iconColor)
                .fontColor(this.fontColor)
                .build();
    }

    public TodoCategoryDto(TodoCategory entity) {
        of(entity);
    }

    @Override
    public TodoCategoryDto of(TodoCategory todoCategory) {
        PropertyMap<TodoCategory, TodoCategoryDto> map = new PropertyMap<TodoCategory, TodoCategoryDto>() {
            @Override
            protected void configure() {

            }
        };

        return ModelConverter.map(map, todoCategory, TodoCategoryDto.class);
    }

}
