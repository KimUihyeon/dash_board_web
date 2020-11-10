package com.sutdy.dashboard.setting.inteceptor;

import com.sutdy.dashboard.service.AccountService;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.exception.impl.JwtAuthException;
import com.sutdy.dashboard.setting.exception.impl.JwtTimeoutException;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * @author kuh
 * @since 2020.05.09
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private AccountService accountService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    private final static String TOKEN_NAME = ApplicationStringConfig.JWT_HEADER_KEY_NAME;

    @Value("${jwt_time_out_message}")
    private String JWT_TIME_OUT_MESSAGE;

    @Value("${jwt_no_auth_message}")
    private String JWT_NO_AUTH_MESSAGE;

    private final String OPTIONS = HttpMethod.OPTIONS.name();


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception, JwtAuthException, JwtTimeoutException {

        // preflight 처리
        final String currentMethod = request.getMethod();

        if (currentMethod.toUpperCase().equals(OPTIONS)) {

            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
            response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, withcredentials ," + TOKEN_NAME);
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }

        final String jwt = request.getHeader(TOKEN_NAME);
        AuthResponse authResponse = this.accountService.auth(jwt);

        switch (authResponse.getAuthType()) {
            case Auth:
                return super.preHandle(request, response, handler);

            case TimeOut:
                throw new JwtTimeoutException(JWT_TIME_OUT_MESSAGE);

            case WrongEncounter:
            case NoAuth:
                throw new JwtAuthException(JWT_NO_AUTH_MESSAGE);
        }

        return super.preHandle(request, response, handler);
    }

}
