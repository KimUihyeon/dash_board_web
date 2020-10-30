package com.sutdy.dashboard.setting;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sutdy.dashboard.domain.todo.QTodo;
import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.service.TodoService;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.07.31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class QueryDslTest {

    private Logger logger = LoggerFactory.getLogger(QueryDslTest.class);

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private TodoService todoService;

//    @Autowired
//    private  TodoRepositoryImpl todoRepositoryImpl;

    @Autowired
    private TodoRepository todoRepository;

    private List<Todo> createTodoList(int dataCount) {
        List<Todo> tempDatas = new ArrayList<>();

        for (int i = 0; i < dataCount; i++) {

            Todo todo = Todo.builder()
                    .title("테스트 타이틀 " + i)
                    .cDate(DateUtil.now())
                    .contents("테스트 내용__" + i + "\n" + "HibernateTest 에서 생성됨.")
                    .build();
            tempDatas.add(todo);
        }

        return this.todoRepository.saveAll(tempDatas);
    }


    /**
     * jpaQueryFactory 클래스 주입 테스트
     */
    @Test
    @Rollback
    @Transactional
    public void jpaQueryFactory_주입_테스트() {
        if (this.jpaQueryFactory == null) {
            logger.error("jpaQueryFactory null");
        }
        Assert.assertNotNull(this.jpaQueryFactory);
    }


    /**
     * query Dsl 세팅 기능테스트.
     */
    @Test
    @Rollback
    @Transactional
    public void queryDsl_리스트_테스트() {

        // given
        int dataCount = 30;
        List<Todo> jpaTodo = createTodoList(dataCount);
        List<Long> pks = jpaTodo.stream()
                .map(t -> t.getId()).collect(Collectors.toList());

        logger.debug("jpaTodo size => " + jpaTodo.size());

        for (long pk : pks) {
            logger.debug("Todo Test pk -> " + pk);
        }

        // then
        List<Todo> todoList = this.jpaQueryFactory.selectFrom(QTodo.todo)
                .where(QTodo.todo.id.in(pks))
                .fetch();

        logger.debug("todoList size => " + jpaTodo.size());


        // when
        boolean success = true;
        for (Todo queryDslTodo : todoList) { // DataValidation
            long pk = queryDslTodo.getId();
            Todo findTodo = jpaTodo.stream().filter(t -> t.getId() == pk).findFirst().orElse(null);
            if (findTodo == null) {
                success = false;
                logger.error("error not find Entity -> " + pk);
                Assert.assertTrue(false);
            }

        }

        if (success
                && (todoList.size() == 0 || todoList.size() != jpaTodo.size())) { // Collection Size Validation
            success = false;
        }

        Assert.assertTrue(success);
    }


    @Test
    @Rollback
    @Transactional
    public void queryDsl_타이틀_검색_테스트() {

        // given
        int dataCount = 30;
        List<Todo> jpaTodo = createTodoList(dataCount);
        List<Long> pks = jpaTodo.stream()
                .map(t -> t.getId()).collect(Collectors.toList());

        logger.debug("jpaTodo size => " + jpaTodo.size());
        for (Todo todo : jpaTodo) {
            logger.debug("jpaTodo title => " + todo.getTitle());
        }

        // then

        List<Todo> findTodo = this.todoRepository.이게왜됨(jpaTodo.get(0).getTitle());


        logger.error("찾을 Todo Title-> " +jpaTodo.get(0).getTitle());
        logger.error("찾은 Todo Title-> " +findTodo.get(0).getTitle());

        Assert.assertTrue(findTodo.size() > 0);
        Assert.assertEquals(findTodo.get(0).getTitle() , jpaTodo.get(0).getTitle());
    }

}