package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.service.AccountService;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.AuthResponseFactory;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

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
    private AccountService accountService;


    /**
     * 로그인 로직!
     * Account 정보 받아서 토큰 및 인증상태로 리턴함
     * @param member
     * @return 인증상태 및 토큰 리턴함.
     */
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AccountDto member) throws Exception {
        AccountDto findMember = this.accountService.login(member);

        try{
            String jwt = JWT.createToken(findMember.getId(), findMember.getName(),3);
            return JWT.auth(jwt);
        }catch (NullPointerException e){

            return AuthResponseFactory.create(AuthEnum.NoAuth);
        }
    }


    /**
     * 회원정보 변경 .. !
     *
     * @param accountRequest
     * @return
     */
    public AccountDto update(@RequestBody AccountDto accountRequest){
        return this.accountService.update(accountRequest.getId() , accountRequest);
    }

    /**
     * 회원가입
     *
     * @param accountRequest  아이디 , 패스워드, 이름
     * @return
     */
    @PostMapping("/signup")
    public AccountDto signup(@RequestBody AccountDto accountRequest) throws NoSuchAlgorithmException {
        return this.accountService.save(accountRequest);
    }


    /**
     * 아이디 존재 유무 체크하기 !
     * @param accountRequest 아이디
     * @return 존재하면 true
     */
    @PostMapping("/existence")
    public Boolean isExistence(@RequestBody AccountDto accountRequest){
        return  this.accountService.findById(accountRequest.getId()) != null;
    }

}
