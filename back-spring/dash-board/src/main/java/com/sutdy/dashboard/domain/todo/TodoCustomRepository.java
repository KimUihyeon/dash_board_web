package com.sutdy.dashboard.domain.todo;

import com.sutdy.dashboard.dto.TodoDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.07.31
 */
public interface TodoCustomRepository {

    List<Todo> findContainsName(String name);

    List<Todo> 이게왜됨(String name);

    List<Todo> todoListWhereCompleteByUserId(String userId); // COMPLATE
    List<Todo> todoListWhereTodayByUserId(String userId); // TODAY
    List<Todo> todoListWhereImportantByUserId(String userId); //IMPORTANT
    List<Todo> todoListByUserIdAndCategoryId(String userId, long categoryId); // CATEGORY

}