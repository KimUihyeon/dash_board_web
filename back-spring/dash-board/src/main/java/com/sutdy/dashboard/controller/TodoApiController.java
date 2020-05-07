package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.service.TodoCategoryService;
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

    @Autowired
    private TodoCategoryService todoCategoryService;


    //////////// todoList

    @GetMapping("/list")
    public List<TodoDto> getTodoList(String userId, Long categoryId) {
        /**
         * Todo : userid = 이거 널처리 할것 .. ! 널들어오면 Access Exception
         *
         */

//        return todoService.findAll(userId, categoryId);
        return todoService.findAll();
    }

    @DeleteMapping("/item/{id}")
    public TodoDto deleteTodoItem(@PathVariable Long id) {
        return this.todoService.delete(id);
    }


    @PostMapping("/item")
    public TodoDto insertTodoItem(@RequestBody TodoDto todoRequest){
        TodoDto result = this.todoService.save(todoRequest);
        return result;
    }

    @PatchMapping("/item/{id}")
    public TodoDto patchTodoItem(@PathVariable Long id,
                                 @RequestBody TodoDto todoRequest) {
        TodoDto result = this.todoService.update(id, todoRequest);
        return result;
    }

    @GetMapping("/item/{id}")
    public TodoDto getTodoItem(@PathVariable Long id) {
        return todoService.findById(id);
    }


    //////////// todoCategory

    @GetMapping("/category/")
    public List<TodoCategoryDto> getTodoCategories(String userId) {
        /**
         * Todo : userid = 이거 널처리 할것 .. ! 널들어오면 Access Exception
         *
         */
        return this.todoCategoryService.findAll();
    }

    @PatchMapping("/category")
    public TodoCategoryDto patchTodoCategory(@RequestBody TodoCategoryDto categoryRequest) {
        TodoCategoryDto categoryDto = null;

        if (categoryRequest.getId() == null) { // Add
            categoryDto = this.todoCategoryService.save(categoryRequest);
        } else { // Update
            categoryDto = this.todoCategoryService.update(categoryRequest.getId(), categoryRequest);
        }

        return categoryDto;
    }

    ;

}
