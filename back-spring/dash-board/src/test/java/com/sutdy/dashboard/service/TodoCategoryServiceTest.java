package com.sutdy.dashboard.service;

import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    @Rollback(true)
    @Transactional
    public void todoCategory_리스트_불러오기() {
        //given
        for (int i = 0; i < 10; i++) {
            TodoCategoryDto categoryDto = new TodoCategoryDto();
            categoryDto.setCDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT));
            this.todoCategoryService.save(categoryDto);
        }

        //when
        List<TodoCategoryDto> categories = this.todoCategoryService.findAll();

        //then
        categories.forEach((t) -> {
            System.out.println(t.toString());
        });
        Assert.assertNotNull(categories);
        Assert.assertTrue(categories.size() > 0);
    }


    @Test
    @Rollback(true)
    @Transactional
    public void todoCategory_저장_테스트() {
        //given
        TodoCategoryDto category = TodoCategoryDto.builder()
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(),
                        ApplicationStringConfig.DATE_FORMAT))
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
    public void todoCategory_수정_테스트() {
        //given
        TodoCategoryDto category = TodoCategoryDto.builder()
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(),
                        ApplicationStringConfig.DATE_FORMAT))
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
