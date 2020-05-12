package com.sutdy.dashboard.setting.inteceptor;

import com.sutdy.dashboard.dto.MemberDto;
import com.sutdy.dashboard.service.MemberService;
import com.sutdy.dashboard.service.interfacies.IAuthenticationService;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
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
    private MemberService memberService;

    Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        return true;
//
//        Enumeration datas = request.getHeaderNames();
//
//        while (datas.hasMoreElements()) {
//            String key = datas.nextElement().toString();
//            String value = request.getHeader(key);
//            logger.info("key : " + key + "\t||\tvalue: " + value);
//        }
//
//        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[1];
//        String jwt2 = request.getHeader("authorization");
//        try {
//
//            AuthResponse jwtState = JWT.authJwt(jwt);
//
//            switch (jwtState.getAuthType()) {
//                case Auth: {
//                    try {
//                        MemberDto findMember = memberService.findById(jwtState.getId());
//
//                        if (findMember != null) {
//                            return true;
//                        } else {
//                            response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                            return false;
//                        }
//                    } catch (Exception e) {
//                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                        return false;
//                    }
//                }
//                case WrongEncounter:
//                case NoAuth:
//                case TimeOut: {
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                    return false;
//                }
//            }
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        }
//
//        return super.preHandle(request, response, handler);
    }

}
