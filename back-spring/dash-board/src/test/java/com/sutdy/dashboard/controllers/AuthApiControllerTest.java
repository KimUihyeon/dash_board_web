package com.sutdy.dashboard.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sutdy.dashboard.controller.AuthApiController;
import com.sutdy.dashboard.controller.TodoApiController;
import com.sutdy.dashboard.domain.system.SystemErrorRepository;
import com.sutdy.dashboard.service.AccountService;
import com.sutdy.dashboard.service.SystemErrorService;
import com.sutdy.dashboard.service.TodoCategoryService;
import com.sutdy.dashboard.service.TodoService;
import com.sutdy.dashboard.setting.exception.ErrorResponse;
import com.sutdy.dashboard.setting.exception.ExceptionAdvice;
import com.sutdy.dashboard.setting.filters.AuthenticationFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author kuh
 * @since 2020.05.14
 */

@WebMvcTest(controllers = TodoApiController.class)
@RunWith(SpringRunner.class)
public class AuthApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new AuthenticationFilter())
                .build();
    }


    public AuthApiControllerTest(){
//                .standaloneSetup(TodoApiController.class)
//                .setControllerAdvice(new ExceptionAdvice())
//                .build();
    }

    @Test
    public void auth_Jwt_null_전송_테스트() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/todo/item/1").header("authentication", ""))
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new JsonMapper();
        ErrorResponse error = objectMapper.readValue(content, ErrorResponse.class);

    }
}
