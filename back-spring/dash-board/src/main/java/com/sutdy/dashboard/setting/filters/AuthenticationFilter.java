package com.sutdy.dashboard.setting.filters;

import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.exception.impl.JwtTimeoutException;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.AuthResponseFactory;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.rmi.AccessException;
import java.util.Enumeration;
import java.util.List;

@WebFilter( urlPatterns = "/*")
public class AuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException  {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, AccessException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        AuthResponse authResponse = AuthResponse.builder().authType(AuthEnum.NoAuth).build();
        try{
            String authentication  = String.valueOf(httpServletRequest.getHeader("authentication"));
            authResponse = JWT.auth(authentication);
        }
        catch (Exception e) {
            //TODO : 차후 config 파일 만들기
            throw new AccessException("권한이 없습니다.");
        }

        
        switch (authResponse.getAuthType()){
            default:
            case NoAuth:
            case WrongEncounter:{ // 잘못된 접근 , 인증실패
                throw new AccessException("권한이 없습니다.");
            }
            case TimeOut:{ // 타입아웃
                try {
                    // TODO : 이부분 테스트 필요
                    throw new JwtTimeoutException();
                } catch (JwtTimeoutException e) {
                    e.printStackTrace();
                }
            }
            case Auth:{
                chain.doFilter(request, response);
                break;
            }
        }


    }

    @Override
    public void destroy() {

    }
}

class SpaceException extends Exception {

    SpaceException(String msg) {

        super(msg);

    }

}
