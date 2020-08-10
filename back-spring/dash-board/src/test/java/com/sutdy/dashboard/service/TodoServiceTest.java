package com.sutdy.dashboard.service;

import com.sun.xml.bind.v2.TODO;
import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.common.SearchParams;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.rmi.AccessException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.05.05
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoCategoryService todoCategoryService;

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    @Rollback(true)
    public void todo_저장_테스트() {
        //given
        TodoDto dto = TodoDto.builder()
                .date(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .memo("memo test Logic")
                .title("title test Logic")
                .build();

        //when
        TodoDto savedDto = this.todoService.save(dto);
        TodoDto findDto = this.todoService.findById(savedDto.getId());

        //then
        System.out.println(savedDto.toString());
        System.out.println(findDto.toString());
        Assert.assertTrue(savedDto != null && savedDto.getId() > 0);
        Assert.assertTrue(savedDto.toString().equals(findDto.toString()));
    }


    @Test
    @Transactional
    @Rollback(true)
    public void todo_리스트_가져오기_complete() throws AccessException {
        //given
        String id = "testset@naver.com";
        AccountDto accountDto = new AccountDto();
        accountDto.setPw("123123213");
        accountDto.setCDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT));
        accountDto.setName("테스트 중");
        accountDto.setId(id);

        AccountDto savedAccount = null;
        try {
            savedAccount = this.accountService.save(accountDto);
        } catch (Exception e) {

        }


        int k = 0;
        long categoryId = -1;
        for (int i = 0; i < 10; i++) {
            TodoCategoryDto categoryDto = new TodoCategoryDto();
            categoryDto.setTitle("테스트 카테고리 " + i);
            categoryDto.setUserId(savedAccount.getId());

            TodoCategoryDto savedCategoryDto = this.todoCategoryService.save(categoryDto);
            if( i == 9){
                categoryId = savedCategoryDto.getId();
            }

            for(int j = 0 ; j < 4; j ++){
                TodoDto dto = TodoDto.builder()
                        .date(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                        .memo("memo test Logic")
                        .title("title test Logic " + k)
                        .categoryId(savedCategoryDto.getId())
                        .build();

                dto.setTodoComplete(true);// 타입 조건변경
                this.todoService.save(dto);
                k++;
            }
        }


        SearchParams params = new SearchParams();
        params.setId(categoryId);
        params.getFilterDetail().put("userId", id);

        params.setFilter("COMPLETE");// 타입 조건변경

        //when
        List<TodoDto> datas  = this.todoService.selectTodoListByUserIdAndFlag(params);
        List<Todo> todosss = this.todoRepository.findAll();

        //then
        Assert.assertNotNull(datas);
        Assert.assertTrue(datas.size() > 0);
        datas.forEach((t) -> {
            // 타입 조건변경
            Assert.assertTrue(t.isTodoComplete());
            System.out.println(t.toString());
        });
    }

    @Test
    @Transactional
    @Rollback(true)
    public void todo_리스트_가져오기_toDay() throws AccessException {
        //given
        String id = "testset@naver.com";
        AccountDto accountDto = new AccountDto();
        accountDto.setPw("123123213");
        accountDto.setCDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT));
        accountDto.setName("테스트 중");
        accountDto.setId(id);

        AccountDto savedAccount = null;
        try {
            savedAccount = this.accountService.save(accountDto);
        } catch (Exception e) {

        }


        int k = 0;
        long categoryId = -1;
        for (int i = 0; i < 10; i++) {
            TodoCategoryDto categoryDto = new TodoCategoryDto();
            categoryDto.setTitle("테스트 카테고리 " + i);
            categoryDto.setUserId(savedAccount.getId());

            TodoCategoryDto savedCategoryDto = this.todoCategoryService.save(categoryDto);
            if( i == 9){
                categoryId = savedCategoryDto.getId();
            }

            for(int j = 0 ; j < 4; j ++){
                TodoDto dto = TodoDto.builder()
                        .date(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                        .memo("memo test Logic")
                        .title("title test Logic " + k)
                        .categoryId(savedCategoryDto.getId())
                        .build();

                dto.setToDay(true);// 타입 조건변경
                TodoDto savedDto = this.todoService.save(dto);
                k++;
            }
        }


        SearchParams params = new SearchParams();
        params.setId(categoryId);
        params.getFilterDetail().put("userId", id);

        params.setFilter("TODAY");// 타입 조건변경

        //when
        List<TodoDto> datas  = this.todoService.selectTodoListByUserIdAndFlag(params);

        //then
        Assert.assertNotNull(datas);
        Assert.assertTrue(datas.size() > 0);
        datas.forEach((t) -> {
            // 타입 조건변경
            Assert.assertTrue(t.isToDay() && !t.isTodoComplete());
            System.out.println(t.toString());
        });
    }

    @Test
    @Transactional
    @Rollback(true)
    public void todo_리스트_가져오기_important() throws AccessException {
        //given
        String id = "testset@naver.com";
        AccountDto accountDto = new AccountDto();
        accountDto.setPw("123123213");
        accountDto.setCDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT));
        accountDto.setName("테스트 중");
        accountDto.setId(id);

        AccountDto savedAccount = null;
        try {
            savedAccount = this.accountService.save(accountDto);
        } catch (Exception e) {

        }


        int k = 0;
        long categoryId = -1;
        for (int i = 0; i < 10; i++) {
            TodoCategoryDto categoryDto = new TodoCategoryDto();
            categoryDto.setTitle("테스트 카테고리 " + i);
            categoryDto.setUserId(savedAccount.getId());

            TodoCategoryDto savedCategoryDto = this.todoCategoryService.save(categoryDto);
            if( i == 9){
                categoryId = savedCategoryDto.getId();
            }

            for(int j = 0 ; j < 4; j ++){
                TodoDto dto = TodoDto.builder()
                        .date(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                        .memo("memo test Logic")
                        .title("title test Logic " + k)
                        .categoryId(savedCategoryDto.getId())
                        .build();

                dto.setImportant(true);// 타입 조건변경
                TodoDto savedDto = this.todoService.save(dto);
                k++;
            }
        }


        SearchParams params = new SearchParams();
        params.setId(categoryId);
        params.getFilterDetail().put("userId", id);

        params.setFilter("IMPORTANT");// 타입 조건변경

        //when
        List<TodoDto> datas  = this.todoService.selectTodoListByUserIdAndFlag(params);

        //then
        Assert.assertNotNull(datas);
        Assert.assertTrue(datas.size() > 0);
        datas.forEach((t) -> {
            // 타입 조건변경
            Assert.assertTrue(t.isImportant() && !t.isTodoComplete());
            System.out.println(t.toString());
        });
    }


    @Test
    @Transactional
    @Rollback(true)
    public void todo_리스트_가져오기_category() throws AccessException {
        //given
        String id = "testset@naver.com";
        AccountDto accountDto = new AccountDto();
        accountDto.setPw("123123213");
        accountDto.setCDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT));
        accountDto.setName("테스트 중");
        accountDto.setId(id);

        AccountDto savedAccount = null;
        try {
            savedAccount = this.accountService.save(accountDto);
        } catch (Exception e) {

        }


        int k = 0;
        long categoryId = -1;
        for (int i = 0; i < 10; i++) {
            TodoCategoryDto categoryDto = new TodoCategoryDto();
            categoryDto.setTitle("테스트 카테고리 " + i);
            categoryDto.setUserId(savedAccount.getId());

            TodoCategoryDto savedCategoryDto = this.todoCategoryService.save(categoryDto);
            if( i == 9){
                categoryId = savedCategoryDto.getId();
            }

            for(int j = 0 ; j < 4; j ++){
                TodoDto dto = TodoDto.builder()
                        .date(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                        .memo("memo test Logic")
                        .title("title test Logic " + k)
                        .categoryId(savedCategoryDto.getId())
                        .build();

//                dto.setToDay(true);// 타입 조건변경
                TodoDto savedDto = this.todoService.save(dto);
                k++;
            }
        }


        SearchParams params = new SearchParams();
        params.setId(categoryId);
        params.getFilterDetail().put("userId", id);

        params.setFilter("CATEGORY");// 타입 조건변경

        //when
        List<TodoDto> datas  = this.todoService.selectTodoListByUserIdAndFlag(params);

        //then
        Assert.assertNotNull(datas);
        Assert.assertTrue(datas.size() > 0);

        long finalCategoryId = categoryId;
        datas.forEach((t) -> {
            // 타입 조건변경
            Assert.assertTrue(t.getCategoryId().longValue() == finalCategoryId);
            Assert.assertTrue(!t.isTodoComplete());
            System.out.println(t.toString());
        });
    }

//
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void todo_리스트_가져오기_toDay() throws AccessException {
//        //given
//        String id = "testset@naver.com";
//        AccountDto accountDto = new AccountDto();
//        accountDto.setPw("123123213");
//        accountDto.setCDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT));
//        accountDto.setName("테스트 중");
//        accountDto.setId(id);
//
//        AccountDto savedAccount = null;
//        try {
//            savedAccount = this.accountService.save(accountDto);
//        } catch (Exception e) {
//
//        }
//
//
//        int k = 0;
//        long categoryId = -1;
//        for (int i = 0; i < 10; i++) {
//            TodoCategoryDto categoryDto = new TodoCategoryDto();
//            categoryDto.setTitle("테스트 카테고리 " + i);
//            categoryDto.setUserId(savedAccount.getId());
//
//            TodoCategoryDto savedCategoryDto = this.todoCategoryService.save(categoryDto);
//            if( i == 9){
//                categoryId = savedCategoryDto.getId();
//            }
//
//            for(int j = 0 ; j < 4; j ++){
//                TodoDto dto = TodoDto.builder()
//                        .date(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
//                        .memo("memo test Logic")
//                        .title("title test Logic " + k)
//                        .categoryId(savedCategoryDto.getId())
//                        .build();
//                switch (j){
//                    case 0 : { //COMPLETE
//                        dto.setTodoComplete(true);
//                        break;
//                    }
//                    case 1 : { //TODAY
//                        dto.setToDay(true);
//                        break;
//                    }
//                    case 2 : { //IMPORTANT
//                        dto.setImportant(true);
//                        break;
//                    }
//                    default:
//                    case 3 : {
//                        break;
//                    }
//                }
//                TodoDto savedDto = this.todoService.save(dto);
//                k++;
//            }
//        }
//
//
//        for(int j = 0 ; j < 4; j ++){
//            SearchParams params = new SearchParams();
//            params.setId(categoryId);
//            params.getFilterDetail().put("userId", id);
//
//            switch (j){
//                case 0 : { //COMPLETE
//                    params.setFilter("COMPLETE");
//                    break;
//                }
//                case 1 : { //TODAY
//                    params.setFilter("TODAY");
//                    break;
//                }
//                case 2 : { //IMPORTANT
//                    params.setFilter("IMPORTANT");
//                    break;
//                }
//                default:
//                case 3 : {
//                    params.setFilter("CATEGORY");
//                    break;
//                }
//            }
//            //when
//            List<TodoDto> datas  = this.todoService.selectTodoListByUserIdAndFlag(params);
//
//            //then
//            datas.forEach((t) -> {
//                System.out.println(t.toString());
//            });
//            Assert.assertNotNull(datas);
//            Assert.assertTrue(datas.size() > 0);
//
//            switch (j){
//                case 0 : { //COMPLETE
//                    params.setFilter("COMPLETE");
//                    break;
//                }
//                case 1 : { //TODAY
//                    params.setFilter("TODAY");
//                    break;
//                }
//                case 2 : { //IMPORTANT
//                    params.setFilter("IMPORTANT");
//                    break;
//                }
//                default:
//                case 3 : {
//                    params.setFilter("CATEGORY");
//                    break;
//                }
//            }
//        }
//    }


    @Test
    @Transactional
    @Rollback(true)
    public void todo_삭제_테스트() {
        //given
        TodoDto dto = TodoDto.builder()
                .date(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .memo("memo test Logic")
                .title("title test Logic")
                .todoComplete(true)
                .build();
        TodoDto savedDto = this.todoService.save(dto);

        //when
        TodoDto deletedDto = this.todoService.delete(savedDto.getId());
        TodoDto afterTodo = null;
        try {
            afterTodo = this.todoService.findById(savedDto.getId());
        } catch (Exception e) {
        }

        //then
        Assert.assertNull(afterTodo);
    }


    @Test
    @Transactional
    @Rollback(true)
    public void todo_수정_테스트() {
        //given

        TodoDto todo = TodoDto.builder()
                .date(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .memo("memo test Logic")
                .title("update Test")
                .build();

        TodoDto todoDto = this.todoService.save(todo);

        todoDto.setTitle("업데이트 된 타이틀!");
        //when
        TodoDto updatedDto = this.todoService.update(todoDto.getId(), todoDto);
        TodoDto findDto = this.todoService.findById(updatedDto.getId());

        //then

        if (todoDto.getId() == updatedDto.getId()) {
            System.out.println(updatedDto.toString());
        }

        Assert.assertTrue(!todoDto.getTitle().equals(findDto.getTitle()));
        Assert.assertTrue(!"업데이트 된 타이틀!".equals(updatedDto.getTitle()));
        Assert.assertTrue(todoDto.getId() == findDto.getId());
        Assert.assertTrue(todoDto.getId() == updatedDto.getId());
    }
}
