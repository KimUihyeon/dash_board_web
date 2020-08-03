package com.sutdy.dashboard.dto;

import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * dto - entity
 * modelMapper Test
 *
 * @author kuh
 * @since 08.20.03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TodoDtoTest {

    @Test
    public void TodoDto_엔티티_컨버팅_테스트() {

        String date = "2020-05-08 17:35:59";
        //given
        TodoDto dto = new TodoDto();
        dto.setImportant(true);
        dto.setToDay(true);
        dto.setTodoComplete(true);
        dto.setMemo(" 테스트 내용");
        dto.setTitle("테스트 제목");
        dto.setDate(date);
        dto.setEDate(date);
        dto.setSDate(date);

        //when
        Todo todo = dto.toEntity();

        //then
        Assert.assertEquals(todo.getTitle(), dto.getTitle());
        Assert.assertEquals(todo.getId(), dto.getId());
//        Assert.assertEquals(DateUtil.localDateTimeToString(todo.getCDate(), ApplicationStringConfig.DATE_FORMAT), dto.getDate());
//        Assert.assertEquals(DateUtil.localDateTimeToString(todo.getEDate(), ApplicationStringConfig.DATE_FORMAT), dto.getEDate());
//        Assert.assertEquals(DateUtil.localDateTimeToString(todo.getSDate(), ApplicationStringConfig.DATE_FORMAT), dto.getSDate());
        Assert.assertEquals(todo.getContents(), dto.getMemo());
        Assert.assertEquals(todo.isComplete(), dto.isTodoComplete());
        Assert.assertEquals(todo.isToDay(), dto.isToDay());
        Assert.assertEquals(todo.isImportant(), dto.isImportant());

    }

    @Test
    public void TodoDto_컨버팅_테스트() {

        String date = "2020-05-08 17:35:59";
        //given
        Todo todo = Todo.builder()
                .isImportant(true)
                .toDay(true)
                .toDay(true)
                .contents(" 테스트 내용")
                .title("테스트 제목")
                .cDate(LocalDateTime.now())
                .eDate(LocalDateTime.now())
                .sDate(LocalDateTime.now())
                .build();

        //when
        TodoDto dto = new TodoDto().of(todo);

        //then
        Assert.assertEquals(todo.getTitle(), dto.getTitle());
        Assert.assertEquals(todo.getId(), dto.getId());
//        Assert.assertEquals(DateUtil.localDateTimeToString(todo.getCDate(), ApplicationStringConfig.DATE_FORMAT), dto.getDate());
//        Assert.assertEquals(DateUtil.localDateTimeToString(todo.getEDate(), ApplicationStringConfig.DATE_FORMAT), dto.getEDate());
//        Assert.assertEquals(DateUtil.localDateTimeToString(todo.getSDate(), ApplicationStringConfig.DATE_FORMAT), dto.getSDate());
        Assert.assertEquals(todo.getContents(), dto.getMemo());
        Assert.assertEquals(todo.isComplete(), dto.isTodoComplete());
        Assert.assertEquals(todo.isToDay(), dto.isToDay());
        Assert.assertEquals(todo.isImportant(), dto.isImportant());

    }


    @Test
    public void TodoDto_연쇄_컨버팅_테스트() {

        String date = "2020-05-08 17:35:59";
        //given
        TodoDto dto = new TodoDto();
        dto.setImportant(true);
        dto.setToDay(true);
        dto.setTodoComplete(true);
        dto.setMemo(" 테스트 내용");
        dto.setTitle("테스트 제목");
        dto.setDate(date);
        dto.setEDate(date);
        dto.setSDate(date);

        Todo todo = dto.toEntity();

        //when
        TodoDto reconvertDto = new TodoDto().of(todo);

        //then
        Assert.assertEquals(reconvertDto.getTitle(), dto.getTitle());
        Assert.assertEquals(reconvertDto.getId(), dto.getId());
//        Assert.assertEquals(reconvertDto.getDate(), dto.getDate());
//        Assert.assertEquals(reconvertDto.getEDate(), dto.getEDate());
//        Assert.assertEquals(reconvertDto.getSDate(), dto.getSDate());
        Assert.assertEquals(reconvertDto.getMemo(), dto.getMemo());
        Assert.assertEquals(reconvertDto.isTodoComplete(), dto.isTodoComplete());
        Assert.assertEquals(reconvertDto.isToDay(), dto.isToDay());
        Assert.assertEquals(reconvertDto.isImportant(), dto.isImportant());

    }
}
