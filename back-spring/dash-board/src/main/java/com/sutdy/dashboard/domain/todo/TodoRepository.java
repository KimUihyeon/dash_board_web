package com.sutdy.dashboard.domain.todo;

import com.sutdy.dashboard.domain.common.CustomJpaRepository;
import com.sutdy.dashboard.dto.TodoDto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kuh
 * @since 2020.05.03
 */
public interface TodoRepository extends JpaRepository<Todo, Long> , TodoCustomRepository {
}
