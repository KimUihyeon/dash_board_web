package com.sutdy.dashboard.dto.common;

/**
 *
 * @param <T> entity class
 */
public interface IDtoConverter<T> {

    T toEntity();

    void createDto(T entity);
}
