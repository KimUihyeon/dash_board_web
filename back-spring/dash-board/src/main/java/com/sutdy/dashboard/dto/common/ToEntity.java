package com.sutdy.dashboard.dto.common;

/**
 * DTO -> Entity 변환 인터페이스
 *
 * @param <T> entity class
 */
public interface ToEntity<T> {
    T toEntity();

    void of(T t);
}
