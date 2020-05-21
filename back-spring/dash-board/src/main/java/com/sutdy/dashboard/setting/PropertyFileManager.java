package com.sutdy.dashboard.setting;

import org.springframework.context.annotation.PropertySource;

/**
 * @author kuh
 * @since 2020.05.04
 */
public class PropertyFileManager {

    private  static  String Mode = "dev";

    public final static String ERROR_MGS_PROP = "classpath:application-ErrorMsg.properties";
    public final static String DEV_DATA_SOURCE_PROP = "classpath:application-db-dev.properties";
    public final static String REL_DATA_SOURCE_PROP = "classpath:application-db-rel.properties";


}
