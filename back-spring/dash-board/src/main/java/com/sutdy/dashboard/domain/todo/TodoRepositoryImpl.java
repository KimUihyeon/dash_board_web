package com.sutdy.dashboard.domain.todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kuh
 * @since 2020.07.31
 */

@Repository
public class TodoRepositoryImpl extends QuerydslRepositorySupport implements TodoCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public TodoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Todo.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
//
//    //## 20.08.03 queryDsl안됨
//    @Override
//    public List<Todo> findContainsName(String name) {
//        return null;
//    }
//
//    //## 20.08.03 queryDsl안됨
//    @Override
//    public List<Todo> 이게왜됨(String name) {
//        return null;
//    }


    public List<Todo> findAllTest() {
        return this.jpaQueryFactory.selectFrom(QTodo.todo)
                .fetch();
    }

    @Override
    public List<Todo> findContainsName(String name) {
        return this.jpaQueryFactory.selectFrom(QTodo.todo)
                .where(QTodo.todo.title.contains(name))
                .fetch();
    }

    @Override
    public List<Todo> 이게왜됨(String name) {
        return this.jpaQueryFactory.selectFrom(QTodo.todo)
                .where(QTodo.todo.title.contains(name))
                .fetch();
    }
}