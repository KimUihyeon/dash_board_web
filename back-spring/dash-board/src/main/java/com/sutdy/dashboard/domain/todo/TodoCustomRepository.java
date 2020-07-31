package com.sutdy.dashboard.domain.todo;

import java.util.List;

/**
 * @author kuh
 * @since 2020.07.31
 */
public interface TodoCustomRepository {

    List<Todo> findContainsName(String name);

    List<Todo> 이게왜됨(String name);
}