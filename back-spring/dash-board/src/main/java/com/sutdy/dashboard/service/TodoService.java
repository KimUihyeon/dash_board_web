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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.rmi.AccessException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.05.03
 */

@Service("todoService")
public class TodoService extends BaseCrudService<Todo, TodoDto, Long> {

    private TodoCategoryRepository todoCategoryRepository;

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository,
                       TodoCategoryRepository todoCategoryRepository,
                       TempDataFactory tempDataFactory) {
        super(todoRepository);
        this.todoRepository = todoRepository;
        this.todoCategoryRepository = todoCategoryRepository;
    }


    @Override
    @Transactional
    public TodoDto save(TodoDto dto) {
        Todo todo = dto.toEntity();

        if (dto.getCategoryId() != null) {
            TodoCategory category = todoCategoryRepository
                    .findById(dto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));
            todo.setTodoCategory(category);
        }

        return this.saveEntity(todo);
    }

    @Override
    @Transactional
    public TodoDto update(Long pk, TodoDto dto) {
        Todo todo = this.findEntityById(pk);
        todo.patch(dto);
        return new TodoDto().of(todo);
    }


    public List<TodoDto> selectTodoListByUserIdAndFlag(SearchParams params) throws AccessException {

        String userId = params.getFilterDetail().get("userId").toString();

        switch (params.getFilter().toUpperCase()) {
            case "COMPLETE": {
                return this.todoRepository.todoListWhereCompleteByUserId(userId)
                        .stream()
                        .map(a -> new TodoDto().of(a))
                        .collect(Collectors.toList());
            }
            case "TODAY": {
                return this.todoRepository.todoListWhereTodayByUserId(userId)
                        .stream()
                        .map(a -> new TodoDto().of(a))
                        .collect(Collectors.toList());
            }
            case "IMPORTANT": {
                return this.todoRepository.todoListWhereImportantByUserId(userId)
                        .stream()
                        .map(a -> new TodoDto().of(a))
                        .collect(Collectors.toList());
            }

            case "CATEGORY": {
                return this.todoRepository.todoListByUserIdAndCategoryId(userId, params.getId())
                        .stream()
                        .map(a -> new TodoDto().of(a))
                        .collect(Collectors.toList());
            }
            default:
                throw new AccessException(this.NOT_FIND_DATA);
        }
    }
}
