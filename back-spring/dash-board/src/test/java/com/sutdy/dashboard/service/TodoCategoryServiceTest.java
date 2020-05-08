package com.sutdy.dashboard.service;

import com.fasterxml.jackson.core.JsonParser;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.dto.TodoDto;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kuh
 * @since 2020.05.07
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TodoCategoryServiceTest {

    @Autowired
    private TodoCategoryService todoCategoryService;

    @Test
    public void listTest() {
        //given
        //when
        //then
        List<TodoCategoryDto> categories = this.todoCategoryService.findAll();

        categories.forEach((t) -> {
            System.out.println(t.toString());
        });
        Assert.assertNotNull(categories);
        Assert.assertTrue(categories.size() > 0);
    }


    @Test
    @Transactional
    @Rollback(true)
    public void saveTest() {
        //given
        TodoCategoryDto category = TodoCategoryDto.builder()
                .cDate(LocalDateTime.now().toString())
                .fontColor("black")
                .icon("icon-test")
                .iconColor("block")
                .title("test")
                .build();

        //when
        TodoCategoryDto before = this.todoCategoryService.save(category);
        List<TodoCategoryDto> categories = this.todoCategoryService.findAll();
        //then

        categories.forEach((t) -> {
            System.out.println(t.toString());
        });

        Assert.assertNotNull(before);
        Assert.assertTrue(before.getId() == categories.get(categories.size() - 1).getId());

    }


    @Test
    @Transactional
    @Rollback(true)
    public void updateTest() {
        //given
        TodoCategoryDto category = TodoCategoryDto.builder()
                .cDate(LocalDateTime.now().toString())
                .fontColor("black")
                .icon("icon-test")
                .iconColor("block")
                .title("test")
                .build();

        TodoCategoryDto savedCategory = this.todoCategoryService.save(category);
        savedCategory.setTitle("변경된 카테고리명");

        //when
        TodoCategoryDto updatedCategory = this.todoCategoryService.update(savedCategory.getId(), savedCategory);
        List<TodoCategoryDto> categories = this.todoCategoryService.findAll();
        //then

        categories.forEach((t) -> {
            System.out.println(t.toString());
        });

        Assert.assertTrue(updatedCategory.getTitle().equals(savedCategory.getTitle()));
        Assert.assertTrue(updatedCategory.getId() == savedCategory.getId());

    }


}
