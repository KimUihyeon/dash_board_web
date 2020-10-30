package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.members.AccountRepository;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.util.SecurityStringUtil;
import com.sutdy.dashboard.setting.util.StringUtil;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.AuthResponseFactory;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

/**
 * @author kuh
 * @since 2020.05.06
 */
@Service
public class AccountService extends BaseCrudService<Account, AccountDto, String> {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    public AccountService(AccountRepository accountRepository) {
        super(accountRepository);
    }


    @Override
    @Transactional
    public AccountDto save(AccountDto dto) throws NoSuchAlgorithmException {
        String pw = SecurityStringUtil.encryptSHA256(dto.getPw());
        dto.setPw(pw);
        return this.saveEntity(dto.toEntity());
    }

    @Override
    @Transactional
    public AccountDto update(String pk, AccountDto dto) {
        Account entity = this.findEntityById(pk);
        entity.patch(dto);
        return new AccountDto().of(entity);
    }


    public AccountDto login(String id, String pw) throws Exception {

        String encryptPw = SecurityStringUtil.encryptSHA256(pw);
        Account account = this.jpaRepository
                .findById(id).orElse(null);

        if (account != null && encryptPw.equals(encryptPw)) {
            return new AccountDto().of(account);
        } else {
            return null;
        }
    }

    public AuthResponse auth(String jwt) {
        try {
            AuthResponse response = JWT.auth(jwt);

            AccountDto dto = this.findById(response.getId());
            if(!StringUtil.isEmpty(dto.getDDate())){
                response.setAuthType(AuthEnum.WrongEncounter);
            }
            return response;
        } catch (Exception e) {
            return AuthResponseFactory.create(AuthEnum.NoAuth);
        }
    }
}
