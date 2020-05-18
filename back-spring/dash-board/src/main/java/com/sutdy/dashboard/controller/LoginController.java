package com.sutdy.dashboard.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author kuh
 * @since 2020.05.10
 */
@RestController
@RequestMapping(value = "/api/v1/common" )
public class LoginController {
//
//    @Autowired
//    private MemberService memberService;
//
//    @PostMapping("/login")
//    public AuthResponse login(@RequestBody AccountDto member){
//
//        AccountDto findMember = this.memberService.findByMemberDto(member);
//
//        AuthResponse authResponse = null;
//        if(findMember == null){
//
//            authResponse = AuthResponse.builder()
//                    .authType(AuthEnum.NoAuth)
//                    .IIS(JWT.ISS)
//                    .build();
//        }
//        else {
//            String jwt = JWT.createToken(findMember.getId(), findMember.getName(),3);
//
//            authResponse = AuthResponse.builder()
//                    .id(findMember.getId())
//                    .name(findMember.getName())
//                    .authType(AuthEnum.Auth)
//                    .IIS(JWT.ISS)
//                    .token(jwt)
//                    .build();
//        }
//        return authResponse;
//    }
//
//    @PostMapping("/auth")
//    public AuthResponse auth(@RequestBody AuthRequest authRequest){
//        // // TODO: 2020-05-14 : 나중에 해더 로직으로 변경할것 ..!
//        AuthResponse response = JWT.auth(authRequest.getToken());
//        return response;
//    }
//
//    @PostMapping("/logout")
//    public AccountDto logout(){
//
//        return new AccountDto();
//    }

}
