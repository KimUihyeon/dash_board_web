package com.sutdy.dashboard.domain.common;

import com.sutdy.dashboard.dto.common.AbsDtoConverter;
import com.sutdy.dashboard.dto.common.ToEntity;
import com.sutdy.dashboard.service.common.ServiceErrorMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.05.06
 */
public abstract class CustomJpaRepository<Entity, Dto extends AbsDtoConverter<Entity>>
        extends ServiceErrorMessage implements JpaRepository<Entity , Long> {


//    protected JpaRepository<Entity, Long> jpaRepository;

    private Class<Dto> dtoClass;
    private Class<Entity> entityClass;


    private Dto getDtoInstance(Entity entity) {
        try {
            Constructor<Dto> dtoConstructor =  dtoClass.getConstructor(entityClass);;
            return dtoConstructor.newInstance(entity);
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return null;
    }

    public CustomJpaRepository() {

        Type[] genericTypes = ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments();

        this.entityClass = (Class<Entity>) genericTypes[0];
        this.dtoClass = (Class<Dto>) genericTypes[1];
    }


    protected Dto entitySave(Entity entity) {
        try {
            return getDtoInstance(this.save(entity));
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return null;
    }


    @Transactional
    protected Dto entityDelete(long id) throws DataAccessException {
        Entity entity = null;

        try {
            entity = this.findById(id).orElseThrow(() ->
                    new IllegalArgumentException(NOT_FIND_DATA));

            this.deleteById(id);

            return getDtoInstance(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(FAIL_DELETE_JPA);
        }


    }

    protected List<Dto> entityFindAllById(Iterable<Long> ids){
        return this.findAllById(ids)
                .stream()
                .map(entity->getDtoInstance(entity))
                .collect(Collectors.toList());
    }


    protected Page<Dto> entityFindAll(int page, int size) {
        return this.findAll(PageRequest.of(page, size))
                .map(en -> getDtoInstance(en));
    }

    protected List<Dto> entityFindAll() {

        return this.findAll().stream()
                .map(en -> getDtoInstance(en)).collect(Collectors.toList());
    }

    ;

    protected Entity entityFindById(long id) {
        return this.findById(id).orElseThrow(() ->
                new IllegalArgumentException(NOT_FIND_DATA));
    }

    protected Dto entityFindByIdCastDto(long id) {
        return getDtoInstance(entityFindById(id));
    }

    @Deprecated
    protected Entity entityUpdate(long id, ToEntity<Entity> dto) {
        throw new NotImplementedException();
    }
}
