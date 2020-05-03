package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kuh
 * @since 2020.05.03
 */

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public List<TodoDto> getTestDatas(String userId, Long cateroryId){

        List<TodoDto> list = new ArrayList<>();
        for (int i =0 ;i< 10; i++){

            LocalDateTime date = LocalDateTime.now();
            Todo todo = Todo.builder()
                    .cDate(LocalDateTime.now())
                    .id(Long.parseLong(String.valueOf(i)))
                    .title("제목 _" + i)
                    .contents("메모 _ " + i)
                    .build();

            list.add(new TodoDto(todo));
        }

        return list;
    }


}
