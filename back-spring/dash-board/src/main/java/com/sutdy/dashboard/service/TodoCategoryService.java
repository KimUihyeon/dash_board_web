package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * @author kuh
 * @since 2020.05.06
 */
public class TodoCategoryService extends BaseCrudService<TodoCategory, TodoCategoryDto, Long> {

    public TodoCategoryService(JpaRepository jpaRepository) {
        super(jpaRepository);
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
    public TodoCategoryDto delete(Long pk) {
        return this.entityDelete(pk);
    }

    @Override
    public Page<TodoCategoryDto> findAll(int page, int size) {
        throw new NotImplementedException();
    }

    @Override
    public List<TodoCategoryDto> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public List<TodoCategoryDto> findAllById(Iterable<Long> ids) {
        throw new NotImplementedException();
    }

    @Override
    public TodoCategoryDto findById(Long pk) {
        return this.entityFindByIdCastDto(pk);
    }
}
