package com.sutdy.dashboard.service.common;

import com.sutdy.dashboard.setting.common.SearchParams;
import org.springframework.data.domain.Page;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
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
    T save(T dto) throws NoSuchAlgorithmException;

    @Transactional
    T update(ID pk, T dto) throws NotImplementedException;

    @Transactional
    T delete(ID pk);

    List<T> deleteAll(Iterable<ID> ids);

    T findById(ID pk);

    Page<T> findAll(int page, int size);

    List<T> findAllById(Iterable<ID> ids);
}
