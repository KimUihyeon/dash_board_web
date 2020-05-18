package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.domain.todo.TodoCategoryRepository;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.common.SearchParams;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.05.06
 */

@Service
public class TodoCategoryService extends BaseCrudService<TodoCategory, TodoCategoryDto, Long> {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    public TodoCategoryService(TodoCategoryRepository todoCategoryRepository) {
        super(todoCategoryRepository);
        tempData();
    }

    public List<TodoCategoryDto> getDefaultTodoCategories() {
        List<TodoCategoryDto> defaultData = new ArrayList<>();


        defaultData.add(TodoCategoryDto.builder()
                .canModify(false)
                .cDate(Util.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .title("기본 디렉토리")
                .icon("el-icon-folder-delete")
                .iconColor("white")
                .fontColor("white")
                .build());

        return defaultData;
    }

    private void tempData() {
        List<TodoCategoryDto> list = getDefaultTodoCategories();
        list.forEach(t -> {
            this.save(t);
        });
    }


    @Override
    public TodoCategoryDto save(TodoCategoryDto dto) {
        return this.entitySave(dto.toEntity());
    }

    @Override
    public TodoCategoryDto update(Long pk, TodoCategoryDto dto) {
        TodoCategory entity = this.entityFindById(pk);
        entity.patch(dto);
        return new TodoCategoryDto(entity);
    }

    @Override
    @Transactional
    public TodoCategoryDto delete(Long pk) {

        List<Todo> dd = todoRepository.findAll()
                .stream()
                .filter(t -> {
                    if (t.getTodoCategory() != null && t.getTodoCategory().getId() == pk) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        todoRepository.deleteInBatch(dd);

        return this.entityDelete(pk);
    }

    @Override
    public Page<TodoCategoryDto> findAll(int page, int size) {
        throw new NotImplementedException();
    }

    @Override
    public List<TodoCategoryDto> findAll() {
        return this.jpaRepository.findAll()
                .stream()
                .map(t -> new TodoCategoryDto(t))
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoCategoryDto> findAllById(Iterable<Long> ids) {
        throw new NotImplementedException();
    }

    @Override
    public List<TodoCategoryDto> findAll(SearchParams params) {
        return null;
    }

    @Override
    public TodoCategoryDto findById(Long pk) {
        return this.entityFindByIdCastDto(pk);
    }
}
