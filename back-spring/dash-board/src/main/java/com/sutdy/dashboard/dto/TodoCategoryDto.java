package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.dto.common.AbsDtoConverter;

/**
 * @author kuh
 * @since 2020.05.06
 */
public class TodoCategoryDto extends AbsDtoConverter<TodoCategory> {


    public TodoCategoryDto(TodoCategory entity){
        createDto(entity);
    }


    @Override
    public TodoCategory toEntity() {
        return null;
    }

    @Override
    public void createDto(TodoCategory entity) {

    }
}
