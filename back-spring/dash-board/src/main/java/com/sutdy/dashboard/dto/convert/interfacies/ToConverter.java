package com.sutdy.dashboard.dto.convert.interfacies;

/**
 * DTO -> Entity 변환 인터페이스
 *
 * @param <T> entity class
 */
public interface ToConverter<T, Dto> {
    T toEntity();

    Dto of(T t);
}
