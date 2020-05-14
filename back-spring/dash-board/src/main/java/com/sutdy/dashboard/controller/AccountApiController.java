package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.dto.MemberDto;
import com.sutdy.dashboard.service.MemberService;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 계정 관련 !
 *
 * 계정 추가, 로그인, 로그아웃, 계정찾기,
 *
 * @author kuh
 * @since 2020.05.14
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountApiController {

    @Autowired
    private MemberService memberService;


    /**
     * 로그인 로직!
     * Member 정보 받아서 토큰 및 인증상태로 리턴함
     * @param member
     * @return 인증상태 및 토큰 리턴함.
     */
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

    /**
     * 회원가입
     *
     * @param memberRequest  아이디 , 패스워드, 이름
     * @return
     */
    @PostMapping("/signup")
    public MemberDto signup(@RequestBody MemberDto memberRequest){
        return this.memberService.save(memberRequest);
    }

    /**
     * 아이디 존재 유무 체크하기 !
     * @param memberRequest 아이디
     * @return 존재하면 true
     */
    @PostMapping("/existence")
    public Boolean isExistence(@RequestBody MemberDto memberRequest){
        return  this.memberService.findById(memberRequest.getId()) == null;
    }

}
