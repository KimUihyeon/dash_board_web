package com.sutdy.dashboard.controller;

import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.service.TodoCategoryService;
import com.sutdy.dashboard.service.TodoService;
import com.sutdy.dashboard.setting.common.SearchParams;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
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
    public List<TodoDto> getTodoList(String userId, String filter, Long categoryId,
                                     HttpServletResponse response, PagedResourcesAssembler assembler) throws IOException {
        /**
         * Todo : userid = 이거 널처리 할것 .. ! 널들어오면 Access Exception
         *
         */
//        return todoService.findAll(userId, categoryId);
        if (userId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }

        SearchParams params = new SearchParams();
        params.setFilter(filter);
        params.setId(categoryId);
        return todoService.findAll(params);
    }

    @DeleteMapping("/item/{id}")
    public TodoDto deleteTodoItem(@PathVariable Long id) {
        return this.todoService.delete(id);
    }


    @PostMapping("/item")
    public TodoDto insertTodoItem(@RequestBody TodoDto todoRequest) {
        todoRequest.setDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT));
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

    @GetMapping("/categories/{userId}")
    public List<TodoCategoryDto> getTodoCategories(@PathVariable String userId) {
        /**
         * Todo : userid = 이거 널처리 할것 .. ! 널들어오면 Access Exception
         *
         */
        return this.todoCategoryService.findAll();
    }

    @DeleteMapping("/category/{id}")
    public TodoCategoryDto deleteTodoCategory(@PathVariable Long id, String userId) {
        return this.todoCategoryService.delete(id);
    }

    @PostMapping("/category")
    public TodoCategoryDto inertTodoCategory(@RequestBody TodoCategoryDto todoCategoryDto) {
        todoCategoryDto.setCDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT));
        return this.todoCategoryService.save(todoCategoryDto);
    }

    @PatchMapping("/category/{id}")
    public TodoCategoryDto patchTodoCategory(@PathVariable Long id,
                                             @RequestBody TodoCategoryDto categoryRequest) {
        return this.todoCategoryService.update(id, categoryRequest);
    }
}
