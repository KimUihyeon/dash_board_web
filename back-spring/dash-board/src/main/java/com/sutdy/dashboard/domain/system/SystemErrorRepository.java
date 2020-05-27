package com.sutdy.dashboard.domain.system;

import com.sutdy.dashboard.domain.members.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kuh
 * @since 2020.05.28
 */
public interface SystemErrorRepository extends JpaRepository<SystemError, Long> {
}
