package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kuh
 * @since 2020.05.09
 */

@RestController
@RequestMapping("/api/v1/member")
public class MemberApiController {

    @Autowired
    private MemberService memberService;

    public MemberApiController(){
        MemberService d = this.memberService;
    }

}
