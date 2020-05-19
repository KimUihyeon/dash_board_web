package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.members.AccountRepository;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.common.SearchParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * TODO : 로그인관련 서비스로 로직 세로만들기 !
 *
 * @author kuh
 * @since 2020.05.06
 */
@Service
public class AccountService extends BaseCrudService<Account, AccountDto, String> {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        super(accountRepository);
        tempDate();
    }


    public void tempDate() {
        String id = "admin@naver.com";
        logger.info("account Save -> " + id );

        this.save(AccountDto.builder()
                .id(id)
                .name("김의현")
                .pw("12312321")
                .build());
    }

    @Override
    public AccountDto save(AccountDto dto) {
        return this.entitySave(dto.toEntity());
    }

    @Override
    @Transactional
    public AccountDto update(String pk, AccountDto dto) {
        Account entity = this.entityFindById(pk);
        entity.patch(dto);
        return new AccountDto(entity);
    }

    @Override
    public AccountDto delete(String pk) {
        return this.entityDelete(pk);
    }

    @Override
    public Page<AccountDto> findAll(int page, int size) {
        throw new NotImplementedException();
    }

    @Override
    public List<AccountDto> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public List<AccountDto> findAllById(Iterable<Long> ids) {
        throw new NotImplementedException();
    }

    @Override
    public List<AccountDto> findAll(SearchParams params) {
        return null;
    }

    @Override
    public AccountDto findById(String pk) {
        Account member = this.jpaRepository.findById(pk).orElse(null);
        if (member == null) {
            return null;
        }
        return new AccountDto(member);
    }


    public AccountDto findByMemberDto(AccountDto dto) {

        Account member = this.jpaRepository
                .findById(dto.getId()).orElse(null);

        if (member != null) {
            return new AccountDto(member);
        } else {
            return null;
        }
    }

}