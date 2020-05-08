package com.sutdy.dashboard.service.common;

import com.sutdy.dashboard.setting.common.SearchParams;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.util.List;

/**
 * service 공통 인터페이스!
 * 미구현 메서드에는 Exception을 달아주자.
 *
 * @author kuh
 * @since 2020.04.08
 */
public interface IServiceBase<T, ID> {


    @Transactional
    T save(T dto);

    @Transactional
    T update(ID pk, T dto);

    @Transactional
    T delete(ID pk);

    Page<T> findAll(int page, int size);

    List<T> findAll();

    List<T> findAllById(Iterable<Long> ids);

    List<T> findAll(SearchParams params);

    T findById(ID pk);


}
