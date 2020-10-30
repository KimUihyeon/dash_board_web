package com.sutdy.dashboard.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sutdy.dashboard.controller.AuthApiController;
import com.sutdy.dashboard.controller.TodoApiController;
import com.sutdy.dashboard.domain.system.SystemErrorRepository;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.service.AccountService;
import com.sutdy.dashboard.service.SystemErrorService;
import com.sutdy.dashboard.service.TodoCategoryService;
import com.sutdy.dashboard.service.TodoService;
import com.sutdy.dashboard.setting.exception.ErrorResponse;
import com.sutdy.dashboard.setting.exception.ExceptionAdvice;
import com.sutdy.dashboard.setting.filters.AuthenticationFilter;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author kuh
 * @since 2020.05.14
 */

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TodoApiController.class)
public class AuthApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Mock
    private RequestAttributes attrs;

    @MockBean
    private TodoService todoService;

    @MockBean
    private TodoCategoryService todoCategoryService;

    @MockBean
    private AccountService accountService;

    @MockBean
    private SystemErrorService systemErrorService;

    @MockBean
    private TodoRepository todoRepository;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilter(new AuthenticationFilter())
                .build();

        MockitoAnnotations.initMocks(this);
        RequestContextHolder.setRequestAttributes(attrs);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }


    public AuthApiControllerTest() {
//                .standaloneSetup(TodoApiController.class)
//                .setControllerAdvice(new Exce ptionAdvice())
//                .build();
    }

    @Test
    public void auth_Jwt_null_전송_테스트() throws Exception {

        // TODO: 2020-10-30 컨트롤러는 타는데 Service 결과가 null임
        // given
        TodoDto dto = TodoDto.builder()
                .title("API 테스트 Todo")
                .build();
        TodoDto todo = TodoDto.builder().title(dto.getTitle()).build();
        given(this.todoService.save(dto)).willReturn(todo);
        // when
        String userId = "admin@admin.com";
        String userName = "test Name";

        String token = JWT.createToken(userId, userName, 3);
        MvcResult result = this.mockMvc.perform(get("/api/v1/todo/item/" + todo.getId())
                .header("authentication", ""))
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        ObjectMapper objectMapper = new JsonMapper();

        // then


        ErrorResponse error = objectMapper.readValue(content, ErrorResponse.class);

    }
}
