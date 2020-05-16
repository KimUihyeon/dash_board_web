package com.sutdy.dashboard.setting.util.auth;

import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AuthResponseFactory {

    public static AuthResponse create(AuthEnum type){
        return create(null,null,type,  null,null);
    }

    public static AuthResponse create(String id, String name, AuthEnum type){
        return create(id,name,type, null ,null);
    }

    public static AuthResponse create(String id, String name, AuthEnum type, String token){
        return create(id,name,type,token,null);
    }
    public static AuthResponse create(String id, String name, AuthEnum type, String token, Long tokenExp){

        AuthResponse authResponse = AuthResponse.builder()
                .IIS(ApplicationStringConfig.WEB_URL)
                .authType(type)
                .id(id)
                .name(name)
                .token(token)
                .build();

        if(tokenExp != null){
            String tokenEndDate = new SimpleDateFormat(ApplicationStringConfig.DATE_FORMAT).format(new Date(tokenExp));
            authResponse.setAuthEndDate(tokenEndDate);
        }
        return authResponse;
    }
}
