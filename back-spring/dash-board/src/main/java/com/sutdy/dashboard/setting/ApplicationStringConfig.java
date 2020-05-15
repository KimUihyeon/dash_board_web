package com.sutdy.dashboard.setting;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.DateFormat;

/**
 * @author kuh
 * @since 2020.05.08
 */
public class ApplicationStringConfig {
    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String JWT_ENCRYPTION_KEY = "rladmlgusWkdWkdaos";
    public final static String WEB_URL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toString() +".com";
}
