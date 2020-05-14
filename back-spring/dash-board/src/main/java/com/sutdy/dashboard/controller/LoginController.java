package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.dto.MemberDto;
import com.sutdy.dashboard.service.MemberService;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthRequest;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kuh
 * @since 2020.05.10
 */
@RestController
@RequestMapping(value = "/api/v1/common" )
public class LoginController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody MemberDto member){

        MemberDto findMember = this.memberService.findByMemberDto(member);

        AuthResponse authResponse = null;
        if(findMember == null){

            authResponse = AuthResponse.builder()
                    .authType(AuthEnum.NoAuth)
                    .IIS(JWT.ISS)
                    .build();
        }
        else {
            String jwt = JWT.create(findMember.getId(), findMember.getName(),3);

            authResponse = AuthResponse.builder()
                    .id(findMember.getId())
                    .name(findMember.getName())
                    .authType(AuthEnum.Auth)
                    .IIS(JWT.ISS)
                    .token(jwt)
                    .build();
        }
        return authResponse;
    }
    
    @PostMapping("/auth")
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
