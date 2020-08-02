package com.sutdy.dashboard.dto.common;

/**
 * DTO -> Entity 변환 인터페이스
 *
 * @param <T> entity class
 *
 * TODO : 미사용 예정 ToEntity로 작업 끝나면 ToEntity_Old, AbsDtoConverter 삭제할것 !
 */

@Deprecated
public interface ToEntity_Old<T> {
    T toEntity();

    T createDto(T t);
}
