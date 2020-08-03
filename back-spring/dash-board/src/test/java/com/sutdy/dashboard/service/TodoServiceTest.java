package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.todo.Todo;
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

        SearchParams params = new SearchParams();
        params.setFilter("CATEGORY");
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

        SearchParams params = new SearchParams();
        params.setFilter("CATEGORY");
        params.getFilterDetail().put("userId" , userId);
        List<TodoDto> dbTodoList = this.todoService.findAll(params);
        int deleteObjectIndex = (int) Math.random() * dbTodoList.size();
        TodoDto deleteTarget = dbTodoList.get(deleteObjectIndex);


        //when
        TodoDto deletedDto = this.todoService.delete(deleteTarget.getId());

        //then
        List<TodoDto> deleteBeforeList = this.todoService.findAll(params);

        Assert.assertTrue(deleteBeforeList.size() != dbTodoList.size());
        Assert.assertTrue(deletedDto.getId() == deleteTarget.getId());
    }


    @Test
    @Transactional
    @Rollback(true)
    public void todo_수정_테스트() {
        //given
        Todo todo = Todo.builder()
                .title("update Test")
                .contents("")
                .cDate(LocalDateTime.now())
                .build();

        TodoDto todoDto = this.todoService.saveEntity(todo);

        todoDto.setTitle("업데이트 된 타이틀!");
        //when
        this.todoService.update(todoDto.getId(), todoDto);
        TodoDto updatedDto = this.todoService.findById(todoDto.getId());
        TodoDto findDto = this.todoService.findById(todoDto.getId());

        //then

        if (todoDto.getId() == updatedDto.getId()) {
            System.out.println(updatedDto.toString());
        }

        Assert.assertTrue(!todoDto.getTitle().equals(findDto.getTitle()));
        Assert.assertTrue(todoDto.getId() == findDto.getId());
    }
}
