package com.sutdy.dashboard.setting.util.auth;

import com.sutdy.dashboard.dto.MemberDto;
import com.sutdy.dashboard.setting.util.AppConfig;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kuh
 * @since 2020.05.10
 */
@Data
@Builder
@AllArgsConstructor
public class AuthResponse {

    private AuthEnum authType;

    private String id;

    private String name;

    private String token;

    private String authDate;

    private String authEndDate;

    private String IIS;

    public AuthResponse(){
        this.authDate = new SimpleDateFormat(AppConfig.DATE_FORMAT).format(new Date());
    }

}
