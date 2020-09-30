package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.service.AccountService;
import com.sutdy.dashboard.setting.util.auth.AuthRequest;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AccountService accountService;


    /**
     * 페이지 인증,권한 메소드
     *
     * @param authRequest
     * @return
     */
    @PostMapping()
    public AuthResponse auth(@RequestBody AuthRequest authRequest){
        // // TODO: 2020-05-14 : 나중에 해더 로직으로 변경할것 ..!
        return this.accountService.auth(authRequest.getToken());
    }

    @PostMapping("/logout")
    public AccountDto logout(){
        return new AccountDto();
    }

}
