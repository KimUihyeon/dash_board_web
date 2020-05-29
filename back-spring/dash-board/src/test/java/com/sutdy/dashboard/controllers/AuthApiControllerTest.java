package com.sutdy.dashboard.controllers;

import com.sutdy.dashboard.controller.AuthApiController;
import com.sutdy.dashboard.domain.system.SystemErrorRepository;
import com.sutdy.dashboard.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author kuh
 * @since 2020.05.14
 */

//@WebMvcTest( controllers = AuthApiController.class)
//@RunWith(SpringRunner.class)
public class AuthApiControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AccountService accountService;
//
//    @MockBean
//    private SystemErrorRepository systemErrorRepository;
//
//    @Test
//    public void test() throws Exception {
//
//
//        mockMvc.perform(get("/api/v1/auth"))
//                .andDo(print());
//    }
}
