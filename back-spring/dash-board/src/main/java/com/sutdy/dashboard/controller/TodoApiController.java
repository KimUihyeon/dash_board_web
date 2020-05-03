package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kuh
 * @since 2020.05.03
 */

@RestController
@RequestMapping(value = "/api/v1/todo")
public class TodoApiController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/list")
    public List<TodoDto> getList(String userId, Long categoryId){
        return todoService.getTestDatas(userId, categoryId);
    }
}
