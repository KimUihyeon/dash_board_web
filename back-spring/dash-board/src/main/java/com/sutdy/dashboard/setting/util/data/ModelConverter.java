package com.sutdy.dashboard.setting.util.data;

import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.dto.common.ToEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Function;

/**
 * @author kuh
 * @description Model Mapper lib Extends class
 * https://mvnrepository.com/artifact/org.modelmapper/modelmapper/2.3.2
 * @since 2020.08.02
 */

public class ModelConverter {

    public static <M> M map(PropertyMap propertyMap, Object source, Class<M> m) {
        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(propertyMap);
        return mapper.map(source, m);
    }

    public static <M> M map(Object source, Class<M> m) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(source, m);
    }
}
