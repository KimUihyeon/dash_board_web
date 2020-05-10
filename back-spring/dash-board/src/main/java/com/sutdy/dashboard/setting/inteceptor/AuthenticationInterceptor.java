package com.sutdy.dashboard.setting.inteceptor;

import com.sutdy.dashboard.dto.MemberDto;
import com.sutdy.dashboard.service.MemberService;
import com.sutdy.dashboard.service.interfacies.IAuthenticationService;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
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

    Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration datas = request.getHeaderNames();

        while (datas.hasMoreElements()){
            String key = datas.nextElement().toString();
            String value = request.getHeader(key);
            logger.info("key : " + key + "\t||\tvalue: " + value);
        }

        String data = request.getHeader(HttpHeaders.AUTHORIZATION);
        String jwt = request.getHeader("authorization");
//        if(jwt == null){
//            String jwtStr = jwt.toString();
//            System.out.println(jwtStr);
//            AuthEnum authEnum = JWT.authJwt(jwtStr);
//        }

//        MemberDto dto = memberService.authentication("");
//        request.setAttribute("member", dto);
//        response.sendError(401);
        return super.preHandle(request, response, handler);
    }
}
