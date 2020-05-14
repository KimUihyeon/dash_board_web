package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.dto.MemberDto;
import com.sutdy.dashboard.service.MemberService;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthRequest;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인증관련 컨트롤러 !
 *
 * @author kuh
 * @since 2020.05.14
 */

@RestController
@RequestMapping("/api/v1/auth")
public class AuthApiController {

    @Autowired
    private MemberService memberService;


    @PostMapping()
    public AuthResponse auth(@RequestBody AuthRequest authRequest){
        // // TODO: 2020-05-14 : 나중에 해더 로직으로 변경할것 ..!
        AuthResponse response = JWT.authJwt(authRequest.getToken());
        return response;
    }



    @PostMapping("/logout")
    public MemberDto logout(){

        return new MemberDto();
    }

}
