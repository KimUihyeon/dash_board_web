package com.sutdy.dashboard.service;

import com.sutdy.dashboard.dto.TodoDto;
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


    @Test
    @Transactional
    @Rollback(true)
    public void listTest(){
        //given
        //when
        //then
        List<TodoDto> datas =this.todoService.findAll();

        datas.forEach((t)->{
            System.out.println(t.toString());
        });
        Assert.assertNotNull(datas);
        Assert.assertTrue(datas.size() > 0 );
    }

    @Test
    @Transactional
    @Rollback(true)
    public void saveTest(){
        //given
        TodoDto dto = TodoDto.builder()
                .date(LocalDateTime.now())
                .memo("memo test Logic")
                .title("title test Logic")
                .build();

        //when
        List<TodoDto> dbTodoList = this.todoService.findAll();
        TodoDto savedDto = this.todoService.save(dto);
        TodoDto findDto = this.todoService.findById(savedDto.getId());

        //then
        dbTodoList.forEach(System.out::println);
        System.out.println(savedDto.toString());
        System.out.println(findDto.toString());
        Assert.assertTrue(savedDto != null && savedDto.getId() > 0);
        Assert.assertTrue(savedDto.toString().equals(findDto.toString()));

    }


    @Test
    @Transactional
    @Rollback(true)
    public void deleteTest(){
        //given
        List<TodoDto> dbTodoList = this.todoService.findAll();
        int deleteObjectIndex = (int)Math.random() * dbTodoList.size();
        TodoDto deleteTarget =dbTodoList.get(deleteObjectIndex);


        //when
        TodoDto deletedDto = this.todoService.delete(deleteTarget.getId());

        //then
        List<TodoDto> deleteBeforeList = this.todoService.findAll();

        Assert.assertTrue(deleteBeforeList.size() != dbTodoList.size());
        Assert.assertTrue( deletedDto.getId() == deleteTarget.getId());
    }


    @Test
    @Transactional
    @Rollback(true)
    public void updateTest(){
        //given
        List<TodoDto> dbTodoList = this.todoService.findAll();
        int deleteObjectIndex = (int)Math.random() * dbTodoList.size();
        TodoDto todoDto = dbTodoList.get(deleteObjectIndex);

        todoDto.setTitle("업데이트 된 타이틀!");
        //when
        this.todoService.update(todoDto.getId(), todoDto);
        List<TodoDto> updateBeforeList = this.todoService.findAll();
        TodoDto findDto  = this.todoService.findById(todoDto.getId());

        //then
        updateBeforeList.forEach(t->{
            if(todoDto.getId() == t.getId()){
                System.out.println(t.toString());
            }
        });

        Assert.assertTrue( !todoDto.getTitle().equals(findDto.getTitle()));
        Assert.assertTrue( todoDto.getId() == findDto.getId());
    }
}
