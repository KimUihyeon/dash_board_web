package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.members.Member;
import com.sutdy.dashboard.domain.members.MemberRepository;
import com.sutdy.dashboard.dto.MemberDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.common.SearchParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

/**
 * TODO : 로그인관련 서비스로 로직 세로만들기 !
 * @author kuh
 * @since 2020.05.06
 */
@Service
public class MemberService extends BaseCrudService<Member, MemberDto, String> {

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        super(memberRepository);
    }

    @Override
    public MemberDto save(MemberDto dto) {
        return this.entitySave(dto.toEntity());
    }

    @Override
    @Transactional
    public MemberDto update(String pk, MemberDto dto) {
        Member entity = this.entityFindById(pk);
        entity.patch(dto);
        return new MemberDto(entity);
    }

    @Override
    public MemberDto delete(String pk) {
        return this.entityDelete(pk);
    }

    @Override
    public Page<MemberDto> findAll(int page, int size) {
        throw new NotImplementedException();
    }

    @Override
    public List<MemberDto> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public List<MemberDto> findAllById(Iterable<Long> ids) {
        throw new NotImplementedException();
    }

    @Override
    public List<MemberDto> findAll(SearchParams params) {
        return null;
    }

    @Override
    public MemberDto findById(String pk) {
        return this.entityFindByIdCastDto(pk);
    }
}
