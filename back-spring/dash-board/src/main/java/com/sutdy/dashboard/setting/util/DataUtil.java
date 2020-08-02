package com.sutdy.dashboard.setting.util;

import org.modelmapper.ModelMapper;

import java.util.function.Function;

/**
 * @author kuh
 * @description Model Mapper lib Extends class
 * https://mvnrepository.com/artifact/org.modelmapper/modelmapper/2.3.2
 * @since 2020.08.02
 */
public class DataUtil {

    private static ModelMapper mapper;

    private DataUtil() {
        if (this.mapper == null) {
            this.mapper = new ModelMapper();
        }
    }

    /**
     * 단순한 맵핑에 사용됨.
     * object로 들어온 객체를 R클래스로 생성후 반환시킴.
     *
     * @param object 데이터 맵핑을 시행할 객체
     * @param r 맵핑될 클래스
     * @param <R> 반환타입
     * @return
     */
    public static <R> R mapping(Object object, Class<R> r) {
        return mapper.map(object, r);
    }


    /**
     * 복잡한 맵핑시 사용됨.
     * 맵핑후에 정의된 function을 실행하여
     *
     * @param object 데이터 맵핑을 시행할 객체
     * @param r 맵핑될 클래스
     * @param f Mapping 후에 처리될 function
     * @param <R> 반환타입
     * @return
     */
    public static <R> R mapping(Object object, Class<R> r, Function f){
        return mapper.map(object, r);
    };
}
