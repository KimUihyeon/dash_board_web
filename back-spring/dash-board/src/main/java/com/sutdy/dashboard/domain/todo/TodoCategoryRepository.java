package com.sutdy.dashboard.domain.todo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kuh
 * @since 2020.05.06
 */
public interface TodoCategoryRepository extends JpaRepository<TodoCategory , Long> {
}
