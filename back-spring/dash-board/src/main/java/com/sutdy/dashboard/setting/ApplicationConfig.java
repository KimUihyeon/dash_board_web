package com.sutdy.dashboard.setting;

import com.sutdy.dashboard.setting.inteceptor.AuthenticationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kuh
 * @since 2020.05.12
 */

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("authenticationInterceptor resit");
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/*")
                .excludePathPatterns("/api/v1/common/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("CORS resit");

        String[] origins = {
                "*",
                "http://localhost:3000/"
        };

        registry.addMapping("/**")
                .allowedOrigins(origins)
                .allowedMethods(HttpMethod.GET.toString(),
                        HttpMethod.POST.toString(),
                        HttpMethod.PATCH.toString(),
                        HttpMethod.GET.toString(),
                        HttpMethod.DELETE.toString());
//                .allowedHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
//                        HttpHeaders.AUTHORIZATION);
    }
}
