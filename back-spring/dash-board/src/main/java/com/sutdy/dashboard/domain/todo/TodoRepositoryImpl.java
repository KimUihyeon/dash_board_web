package com.sutdy.dashboard.domain.todo;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sutdy.dashboard.domain.members.QAccount;
import com.sutdy.dashboard.dto.TodoDto;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Todo> todoListWhereCompleteByUserId(String userId) {
        return this.jpaQueryFactory.selectFrom(QTodo.todo)
                .innerJoin(QTodoCategory.todoCategory, QTodo.todo.todoCategory)
                .innerJoin(QAccount.account, QTodoCategory.todoCategory.account)
                .where(QAccount.account.name.eq(userId).and(QTodo.todo.complete.eq(true)))
                .orderBy(QTodo.todo.id.desc())
                .fetch();
    }

    @Override
    public List<Todo> todoListWhereTodayByUserId(String userId) {
        return this.jpaQueryFactory.selectFrom(QTodo.todo)
                .innerJoin(QTodoCategory.todoCategory, QTodo.todo.todoCategory)
                .innerJoin(QAccount.account, QTodoCategory.todoCategory.account)
                .where(
                        QAccount.account.name.eq(userId)
                                .and(
                                        QTodo.todo.complete.eq(false))
                                .and(
                                        QTodo.todo.toDay.eq(true)
                                ))
                .orderBy(QTodo.todo.id.desc())
                .fetch();
    }

    @Override
    public List<Todo> todoListWhereImportantByUserId(String userId) {
        return this.jpaQueryFactory.selectFrom(QTodo.todo)
                .innerJoin(QTodoCategory.todoCategory, QTodo.todo.todoCategory)
                .innerJoin(QAccount.account, QTodoCategory.todoCategory.account)
                .where(
                        QAccount.account.name.eq(userId)
                                .and(
                                        QTodo.todo.complete.eq(false))
                                .and(
                                        QTodo.todo.isImportant.eq(true)
                                ))
                .orderBy(QTodo.todo.id.desc())
                .fetch();
    }

    @Override
    public List<Todo> todoListByUserIdAndCategoryId(String userId, long categoryId) {
        return this.jpaQueryFactory.selectFrom(QTodo.todo)
                .innerJoin(QTodoCategory.todoCategory, QTodo.todo.todoCategory)
                .innerJoin(QAccount.account, QTodoCategory.todoCategory.account)
                .where(
                        QAccount.account.name.eq(userId)
                                .and(
                                        QTodo.todo.complete.eq(false))
                                .and(
                                        QTodoCategory.todoCategory.id.eq(categoryId)
                                ))
                .orderBy(QTodo.todo.id.desc())
                .fetch();
    }

}