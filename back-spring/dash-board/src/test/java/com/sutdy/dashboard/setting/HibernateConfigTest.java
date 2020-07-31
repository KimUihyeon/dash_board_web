package com.sutdy.dashboard.setting;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sutdy.dashboard.domain.todo.QTodo;
import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.service.TodoService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.07.31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HibernateConfigTest {

    private Logger logger = LoggerFactory.getLogger(HibernateConfigTest.class);

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    private List<Todo> createTodoList(int dataCount) {
        List<Todo> tempDatas = new ArrayList<>();

        for (int i = 0; i < dataCount; i++) {

            Todo todo = Todo.builder()
                    .title("테스트 타이틀 " + i)
                    .cDate(LocalDateTime.now())
                    .contents("테스트 내용__" + i + "\n" + "HibernateConfigTest 에서 생성됨.")
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
    public void jpaQueryFactoryInjectionTest() {
        if (this.jpaQueryFactory == null) {
            logger.error("jpaQueryFactory null");
        }
        Assert.assertNotNull(this.jpaQueryFactory);
    }


    /**
     * query Dsl 세팅 기능테스트.
     *
     */
    @Test
    @Rollback
    @Transactional
    public void queryDslTest() {

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
        List<Todo> queryDslTodos =  this.jpaQueryFactory.selectFrom(QTodo.todo)
                .where(QTodo.todo.id.in(pks))
                .fetch();

        logger.debug("queryDslTodos size => " + jpaTodo.size());


        // when
        boolean success = true;
        for(Todo queryDslTodo : queryDslTodos){ // DataValidation
            long pk = queryDslTodo.getId();
            Todo findTodo = jpaTodo.stream().filter(t-> t.getId() == pk).findFirst().orElse(null);
            if(findTodo == null){
                success = false;
                logger.error("error not find Entity -> " + pk);
                Assert.assertTrue(false);
            }

        }

        if(success
                && (queryDslTodos.size() == 0 || queryDslTodos.size() != jpaTodo.size())){ // Collection Size Validation
            success = false;
        }

        Assert.assertTrue(success);
    }
}