package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public List<TodoDto> getList(String userId, Long categoryId) {
//        return todoService.findAll(userId, categoryId);

        return todoService.findAll();
    }

    @DeleteMapping("/{id}")
    public TodoDto delete(@PathVariable Long id) {
        return this.todoService.delete(id);
    }


    @PatchMapping("/")
    public TodoDto patch(@RequestBody TodoDto dto) {
        TodoDto result = null;

        if (dto.getId() == null) {
            result = this.todoService.save(dto);
        } else {
            result = this.todoService.update(dto.getId(), dto);
        }

        return result;
    }

    @GetMapping("/{id}")
    public TodoDto get(@PathVariable Long id) {
        return todoService.findById(id);
    }
}
