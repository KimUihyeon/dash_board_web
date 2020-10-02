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
                .cDate(DateUtil.toTimeStamp(this.cDate, ApplicationStringConfig.DATE_FORMAT))
                .iconColor(this.iconColor)
                .fontColor(this.fontColor)
                .build();
    }


    @Override
    public TodoCategoryDto of(TodoCategory todoCategory) {
        TodoCategoryDto categoryDto = ModelConverter.map(todoCategory, TodoCategoryDto.class);
        categoryDto.setCDate(DateUtil.toString(todoCategory.getCDate(), ApplicationStringConfig.DATE_FORMAT));
        return categoryDto;
    }

}
