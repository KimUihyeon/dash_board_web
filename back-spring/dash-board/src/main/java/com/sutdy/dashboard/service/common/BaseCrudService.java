package com.sutdy.dashboard.service.common;

import com.sutdy.dashboard.dto.common.ToConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.04.16
 */
public abstract class BaseCrudService<Entity, Dto extends ToConverter<Entity, Dto>, ID>
        extends ServiceErrorMessage implements IServiceBase<Dto , ID> {

    private final Logger logger = LoggerFactory.getLogger(BaseCrudService.class);

    protected JpaRepository<Entity, ID> jpaRepository;

    private Class<Dto> dtoClass;
    private Class<Entity> entityClass;


    private Dto getDtoInstance(Entity entity) {
        try {
            Constructor<Dto> dtoConstructor =  dtoClass.getConstructor();
            return ((ToConverter<Entity,Dto>)dtoConstructor.newInstance()).of(entity);
        } catch (Exception e5) {
            e5.printStackTrace();
            logger.error(e5.getMessage());
        }
        return null;
    }


    public BaseCrudService(JpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;

        Type[] genericTypes = ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments();


        this.entityClass = (Class<Entity>) genericTypes[0];
        this.dtoClass = (Class<Dto>) genericTypes[1];
    }


    /**
     * @since 20.08.02
     * @return
     */

    /**
     * @since 20.08.02
     * @param entity 엔티티객체
     * @return
     */
    @Transactional
    public Dto saveEntity(Entity entity) {
        try {
            return getDtoInstance(this.jpaRepository.save(entity));
        } catch (Exception e5) {
            e5.printStackTrace();
            logger.error(e5.getMessage());
        }
        return null;
    }


    @Transactional
    public Dto deleteEntity(ID id) throws DataAccessException {
        Entity entity = null;

        try {
            entity = this.jpaRepository.findById(id).orElseThrow(() ->
                    new IllegalArgumentException(NOT_FIND_DATA));

            this.jpaRepository.deleteById(id);

            return getDtoInstance(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(FAIL_DELETE_JPA);
        }
    }

    public List<Dto> findAllEntityByIds(Iterable<ID> ids){
        return this.jpaRepository.findAllById(ids)
                .stream()
                .map(entity->getDtoInstance(entity))
                .collect(Collectors.toList());
    }

    public Page<Dto> findAllEntity(int page, int size) {
        return this.jpaRepository.findAll(PageRequest.of(page, size))
                .map(en -> getDtoInstance(en));
    }

    public List<Dto> findAllEntity() {
        return this.jpaRepository.findAll().stream()
                .map(en -> getDtoInstance(en)).collect(Collectors.toList());
    };

    protected Entity findEntityById(ID id) {
        return this.jpaRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(NOT_FIND_DATA));
    }

    protected Dto entityFindByIdCastDto(ID id) {
        return getDtoInstance(findEntityById(id));
    }


    @Transactional
    protected Entity entityUpdate(ID id, ToConverter<Entity, Dto> dto) {
        throw new NotImplementedException();
    }

    @Override
    public Dto update(ID pk, Dto dto) {
        throw new NotImplementedException();
    }

    @Override
    public Dto save(Dto dto) throws NoSuchAlgorithmException {
        return saveEntity(dto.toEntity());
    }

    @Override
    public Dto delete(ID pk) {
        return deleteEntity(pk);
    }

    @Override
    public List<Dto> deleteAll(Iterable<ID> ids) {
        return deleteAll(ids);
    }

    @Override
    public Dto findById(ID pk) {
        return getDtoInstance(findEntityById(pk));
    }


    @Override
    public Page<Dto> findAll(int page, int size) {
        return null;
    }

    @Override
    public List<Dto> findAllById(Iterable<ID> ids) {
        return null;
    }
}
