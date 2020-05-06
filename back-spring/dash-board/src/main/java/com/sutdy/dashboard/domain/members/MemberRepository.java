package com.sutdy.dashboard.domain.members;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kuh
 * @since 2020.05.06
 */
public interface MemberRepository extends JpaRepository<Member , String> {
}
