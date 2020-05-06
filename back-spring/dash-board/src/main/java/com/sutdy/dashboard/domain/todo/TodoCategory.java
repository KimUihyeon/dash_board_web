package com.sutdy.dashboard.domain.todo;

import com.sutdy.dashboard.dto.TodoCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author kuh
 * @since 2020.05.03
 */
@Table
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoCategory {


    public void patch(TodoCategoryDto dto){

    }
}
