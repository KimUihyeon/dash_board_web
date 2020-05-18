package com.sutdy.dashboard.service.interfacies;

import com.sutdy.dashboard.dto.AccountDto;

/**
 * @author kuh
 * @since 2020.05.09
 */
public interface IAuthenticationService {

    AccountDto authentication(String jwt);

    AccountDto authentication(AccountDto member);
}
