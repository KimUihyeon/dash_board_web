package com.sutdy.dashboard.domain.todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author kuh
 * @since 2020.07.31
 */

@NoRepositoryBean
public class TodoRepositoryImpl extends QuerydslRepositorySupport implements TodoCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public TodoRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Todo.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    public List<Todo> findAllTest(){
        return this.jpaQueryFactory.selectFrom(QTodo.todo)
                .fetch();
    }
}