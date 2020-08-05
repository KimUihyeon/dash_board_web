package com.sutdy.dashboard.setting.util.data;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import javax.xml.transform.Source;

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
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(source, m);
    }

    public static <M> M map(Object source, Class<M> m) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return mapper.map(source, m);
    }
}
