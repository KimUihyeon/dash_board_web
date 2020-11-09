package com.sutdy.dashboard.setting.filters;

import com.sutdy.dashboard.setting.PropertyFileManager;
import com.sutdy.dashboard.setting.exception.impl.JwtAuthException;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.rmi.AccessException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


/**
 * 미사용중 ...
 */
@WebFilter( urlPatterns = "/*")
@Deprecated
@PropertySource(value = PropertyFileManager.ERROR_MGS_PROP, encoding = "utf-8")
public class AuthenticationFilter implements Filter {

    @Value("${not_access}")
    private String NOT_ACCESS;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException  {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, AccessException{
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();

        List<String> headers = new ArrayList<>();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String header = httpServletRequest.getHeader(headerName);
                headers.add(headerName);
            }
        }


        AuthResponse authResponse = AuthResponse.builder().authType(AuthEnum.NoAuth).build();
        try{
            String authentication  = String.valueOf(httpServletRequest.getHeader("Authorization"));
            authResponse = JWT.auth(authentication);
        }
        catch (Exception e) {
            throw new AccessException(NOT_ACCESS);
        }

        
        switch (authResponse.getAuthType()){
            default:
            case NoAuth:
            case WrongEncounter:{ // 잘못된 접근 , 인증실패
                throw new AccessException(NOT_ACCESS);
            }
            case TimeOut:{ // 타입아웃
                try {
                    throw new JwtAuthException();
                } catch (JwtAuthException e) {
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