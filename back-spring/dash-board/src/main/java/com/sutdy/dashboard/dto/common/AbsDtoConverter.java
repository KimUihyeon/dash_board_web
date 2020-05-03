package com.sutdy.dashboard.dto.common;

/**
 * @author kuh
 * @since 2020.04.17
 */
public abstract class AbsDtoConverter<T> implements IDtoConverter<T> {

    public AbsDtoConverter(){}

    public AbsDtoConverter(T t){
        createDto(t);
    }
}
