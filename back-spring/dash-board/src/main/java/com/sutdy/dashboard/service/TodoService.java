package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.domain.todo.TodoCategoryRepository;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.common.SearchParams;
import com.sutdy.dashboard.setting.util.TempDataFactory;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author kuh
 * @since 2020.05.03
 */

@Service("todoService")
public class TodoService extends BaseCrudService<Todo, TodoDto, Long> {

    private TodoCategoryRepository todoCategoryRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository,
                       TodoCategoryRepository todoCategoryRepository,
                       TempDataFactory tempDataFactory) {
        super(todoRepository);
        this.todoCategoryRepository = todoCategoryRepository;

        tempDataFactory.createTodoDatas(); // // TODO: 2020-05-21 임시데이터 만드는로직.. ! 추후 삭제!
    }


    @Override
    @Transactional
    public TodoDto save(TodoDto dto) {
        Todo todo = dto.toEntity();

        if (dto.getCategoryId() != null) {
            TodoCategory category = todoCategoryRepository
                    .findById(dto.getCategoryId())
                    .orElseThrow(()-> new IllegalArgumentException(NOT_FIND_DATA));
            todo.setTodoCategory(category);
        }

        return this.saveEntity(todo);
    }

    @Override
    @Transactional
    public TodoDto update(Long pk, TodoDto dto) {
        Todo todo = this.findEntityById(pk);
        todo.patch(dto);
        return new TodoDto(todo);
    }


    public List<TodoDto> findAll(SearchParams params) {

        String userId = params.getFilterDetail().get("userId").toString();
        Stream<Todo> queryable = this.jpaRepository.findAll(Sort.by("id").descending()).stream()
                .filter(t-> t.getTodoCategory() != null)
                .filter(t -> t.getTodoCategory().getAccount().getId().equals(userId));

        switch (params.getFilter().toUpperCase()) {
            case "TODAY": {
                return queryable
                        .filter(t -> !t.isComplete())
                        .filter(t -> t.isToDay())
                        .map(a -> new TodoDto(a))
                        .collect(Collectors.toList());
            }
            case "IMPORTANT": {
                return queryable
                        .filter(t -> !t.isComplete())
                        .filter(t -> t.isImportant())
                        .map(a -> new TodoDto(a))
                        .collect(Collectors.toList());
            }
            case "COMPLATE": {
                return queryable
                        .filter(t -> t.isComplete())
                        .map(a -> new TodoDto(a))
                        .collect(Collectors.toList());
            }
            case "CATEGORY": {
                return queryable
                        .filter(t -> !t.isComplete())
                        .filter(t -> t.getTodoCategory().getId() == params.getId())
                        .map(a -> new TodoDto(a))
                        .collect(Collectors.toList());
            }
            default: {
                return queryable
                        .map(a -> new TodoDto(a))
                        .collect(Collectors.toList());
            }
        }
    }


}
