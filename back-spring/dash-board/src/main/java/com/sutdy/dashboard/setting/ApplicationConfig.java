package com.sutdy.dashboard.setting;

import com.sutdy.dashboard.setting.filters.AuthenticationFilter;
import com.sutdy.dashboard.setting.inteceptor.AuthenticationInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

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

        String[] excludePatterns = {
                "/api/v1/common/login",
        };

        registry.addInterceptor(authenticationInterceptor)
                .excludePathPatterns(excludePatterns)
                .addPathPatterns("/**")
                .order(1);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("CORS resit");

        String[] origins = {
                "http://localhost:3000"
        };

        registry.addMapping("/**")
                .allowedOrigins(origins)
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.DELETE.name())
                .allowedHeaders(
                        "Authentication",
                        HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
                        HttpHeaders.WWW_AUTHENTICATE,
                        HttpHeaders.CONTENT_TYPE,
                        HttpHeaders.AUTHORIZATION)
                .allowCredentials(true);
    }


//    @Bean
    public FilterRegistrationBean authFilterConfiguration(){
        List<String> urlPatterns = new ArrayList<>();
        // ##배포용 개발용 구분
        // 실제 배포용
//        urlPatterns.add("/api/v1/todo");
        // 개발용
        urlPatterns.add("/*");


        FilterRegistrationBean authFilter = new FilterRegistrationBean();
        authFilter.setFilter(new AuthenticationFilter());
        authFilter.setOrder(0);
        authFilter.setName("ahthFilter");
        authFilter.setUrlPatterns(urlPatterns);

        return  authFilter;
    }
}
