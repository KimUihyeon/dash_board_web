package com.sutdy.dashboard.setting.inteceptor;

import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.service.AccountService;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);


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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Enumeration datas = request.getHeaderNames();

        while (datas.hasMoreElements()) {
            String key = datas.nextElement().toString();
            String value = request.getHeader(key);
            logger.info("key : " + key + "\t||\tvalue: " + value);
        }

        System.out.println(request.getRequestURI());
        System.out.println("authentication");

        String jwt2 = request.getHeader("authentication");
        try {

            AuthResponse jwtState = JWT.auth(jwt2);

            switch (jwtState.getAuthType()) {
                case Auth: {
                    try {
                        AccountDto findMember = accountService.findById(jwtState.getId());

                        if (findMember != null) {
                            return super.preHandle(request, response, handler);
                        } else {
                            response.sendError(HttpServletResponse.SC_FORBIDDEN);
                            return false;
                        }
                    } catch (Exception e) {
                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
                        return false;
                    }
                }
                case WrongEncounter:
                case NoAuth:
                case TimeOut: {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return false;
                }
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return super.preHandle(request, response, handler);

    }

}
