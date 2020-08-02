package com.sutdy.dashboard.dto.common;

/**
 * @author kuh
 * @since 2020.04.17
 * TODO : 미사용 예정 ToEntity로 작업 끝나면 ToEntity_Old, AbsDtoConverter 삭제할것 !
 *
 */
@Deprecated
public abstract class AbsDtoConverter<T> implements ToEntity_Old<T> {

    public AbsDtoConverter(){}

    public AbsDtoConverter(T t){
        createDto(t);
    }
}
