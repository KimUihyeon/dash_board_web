package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.05.03
 */

@Service
public class TodoService extends BaseCrudService<Todo, TodoDto> {

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        super(todoRepository);
        tempData();
    }


    public void tempData(){

        System.out.println("tempData 생성 --------------->");

        for (int i =0 ;i< 10; i++) {

            LocalDateTime date = LocalDateTime.now();
            Todo todo = Todo.builder()
                    .cDate(LocalDateTime.now())
                    .id(Long.parseLong(String.valueOf(i)))
                    .title("제목 _" + i)
                    .contents("메모 _ " + i)
                    .build();

            this.entitySave(todo);
        }
    }

    @Override
    @Transactional
    public TodoDto save(TodoDto dto) {
        return this.entitySave(dto.toEntity());
    }

    @Override
    @Transactional
    public TodoDto update(long id, TodoDto dto) {
        Todo todo =  this.entityFindById(id);
        todo.patch(dto);
        return dto;
    }

    @Override
    @Transactional
    public TodoDto delete(long id) {
        TodoDto entity = new TodoDto(this.entityFindById(id));
        this.entityDelete(id);

        return entity;
    }

    @Deprecated
    @Override
    public Page<TodoDto> findAll(int page, int size) {
        throw new NotImplementedException();
    }

    @Override
    public List<TodoDto> findAll() {
        return this.jpaRepository.findAll()
                .stream()
                .map(t-> {return new TodoDto(t);})
                .collect(Collectors.toList());
    }

    @Override
    @Deprecated
    public List<TodoDto> findAllById(Iterable<Long> ids) {
        throw new NotImplementedException();
    }

    @Override
    public TodoDto findById(long id) {
        return new TodoDto(entityFindById(id));
    }
}
