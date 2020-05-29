package com.sutdy.dashboard.controllers;

import com.sutdy.dashboard.controller.AccountApiController;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.service.AccountService;
import com.sutdy.dashboard.setting.exception.ExceptionAdvice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.restdocs.operation.Parameters;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author kuh
 * @since 2020.05.14
 */

@WebMvcTest(controllers = AccountApiController.class)
@RunWith(SpringRunner.class)
public class AccountApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private ExceptionAdvice exceptionAdvice;

    private AccountDto getTempData() {
        String id = "admin@naver.com";
        String name = "김의현";
        String pw = "123123";
        return new AccountDto( Account.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .build());
    }

    @Test
    public void signupTest() {


    }

    @Test
    public void existenedTest() throws Exception {

        String userId = "admin@naver.com";
        String notExistId = "admin2@naver.com";

        mockMvc.perform(post("/api/v1/account/existence").param("id", userId))
                .andDo(print());

    }

//    @Test
//    public void updateTest() throws Exception {
//
//        //given
//
//        String id = "admin@naver.com";
//        AccountDto findAccount = this.accountService.findById(id);
//        if (findAccount == null) {
//            findAccount = this.accountService.save(getTempData());
//        }
//
//        MultiValueMap<String, String> param = new Parameters();
//        param.add("id", id);
//        param.add("name", "변경된 이름 .. !" + findAccount.getName());
//
//        //when
//        MvcResult result = mockMvc.perform(post("/api/v1/account/update").params(param))
//                .andDo(print())
//                .andReturn();
//
//
//    }
}
