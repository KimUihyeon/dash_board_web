package com.sutdy.dashboard.setting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kuh
 * @since 2020.05.05
 */

@Configuration
public class CORSConfig implements WebMvcConfigurer {

    public CORSConfig(){
        System.out.println("CORSConfig Load");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        String localVue = "http://localhost:3000";
        registry.addMapping("/**")
            .allowedOrigins(localVue)
        .allowedMethods("GET", "POST","PUT", "DELETE" , "PATCH");
    }
}
