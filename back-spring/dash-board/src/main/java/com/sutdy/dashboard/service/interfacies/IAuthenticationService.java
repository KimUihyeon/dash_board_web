package com.sutdy.dashboard.service.interfacies;

import com.sutdy.dashboard.dto.MemberDto;

/**
 * @author kuh
 * @since 2020.05.09
 */
public interface IAuthenticationService {

    MemberDto authentication(String jwt);

    MemberDto authentication(MemberDto member);
}
