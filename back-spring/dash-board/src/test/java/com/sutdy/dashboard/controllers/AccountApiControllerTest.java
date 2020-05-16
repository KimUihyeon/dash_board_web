package com.sutdy.dashboard.controllers;

import com.sutdy.dashboard.controller.AccountApiController;
import com.sutdy.dashboard.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author kuh
 * @since 2020.05.14
 */

@WebMvcTest( controllers = AccountApiController.class )
@RunWith(SpringRunner.class)
public class AccountApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Test
    public void signupTest(){


    }

    @Test
    public void existenedTest() throws Exception {

        mockMvc.perform(post("/api/v1/account/existence"))
                .andDo(print());


        
    }
}
