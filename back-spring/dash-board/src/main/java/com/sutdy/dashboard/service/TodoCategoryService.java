package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.domain.todo.TodoCategoryRepository;
import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.05.06
 */
@Service
public class TodoCategoryService extends BaseCrudService<TodoCategory, TodoCategoryDto, Long> {

    @Autowired
    public TodoCategoryService(TodoCategoryRepository todoCategoryRepository) {
        super(todoCategoryRepository);
        tempData();
    }

    public List<TodoCategoryDto> getDefualtCategory(){
        List<TodoCategoryDto> defaultData = new ArrayList<>();

        defaultData.add(TodoCategoryDto.builder()
                .canModify(false)
                .cDate(LocalDateTime.now())
                .title("기본 디렉토리")
                .icon("el-icon-folder-delete")
                .iconColor("white")
                .fontColor("white")
                .build());

        return defaultData;
    }

    private void tempData() {
        List<TodoCategoryDto> list = getDefualtCategory();
        list.forEach(t->{
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
    public TodoCategoryDto delete(Long pk) {
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
                .map(t->new TodoCategoryDto(t))
                .collect(Collectors.toList());
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
