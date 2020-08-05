package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.todo.Todo;
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
import java.time.LocalDateTime;
import java.util.List;

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
    private AccountService accountService;

    private String userId = "admin@naver.com";

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
    public void todo_리스트_가져오기() {
        //given
        String id = "testset@naver.com";
        AccountDto accountDto = new AccountDto();
        accountDto.setPw("123123213");
        accountDto.setCDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT));
        accountDto.setName("테스트 중");
        accountDto.setId(id);

        AccountDto savedAccount = null;
        try{
            savedAccount = this.accountService.save(accountDto);
        }catch (Exception e){

        }

        TodoCategoryDto categoryDto = new TodoCategoryDto();
        categoryDto.setTitle("테스트 카테고리");
        categoryDto.setUserId(userId);

        TodoDto dto = TodoDto.builder()
                .date(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .memo("memo test Logic")
                .title("title test Logic")
                .todoComplete(true)
                .build();
        TodoDto savedDto = this.todoService.save(dto);

        SearchParams params = new SearchParams();
        params.setFilter("COMPLATE");
        params.getFilterDetail().put("userId", userId);

        //when

        List<TodoDto> datas = this.todoService.findAll(params);


        //then
        datas.forEach((t) -> {
            System.out.println(t.toString());
        });
        Assert.assertNotNull(datas);
        Assert.assertTrue(datas.size() > 0);
    }

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
        try{
            afterTodo = this.todoService.findById(savedDto.getId());
        }catch (Exception e){
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
