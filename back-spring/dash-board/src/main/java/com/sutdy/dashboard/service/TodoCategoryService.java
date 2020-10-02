package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.members.AccountRepository;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.domain.todo.TodoCategoryRepository;
import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.05.06
 */

@Service
public class TodoCategoryService extends BaseCrudService<TodoCategory, TodoCategoryDto, Long> {

    private AccountRepository accountRepository;

    @Autowired
    public TodoCategoryService(TodoCategoryRepository todoCategoryRepository,
                               AccountRepository accountRepository) {
        super(todoCategoryRepository);
        this.accountRepository = accountRepository;
    }


    public List<TodoCategoryDto> defaultTodoCategories(String userId) {

        List<TodoCategoryDto> defaultData = new ArrayList<>();
        defaultData.add(TodoCategoryDto.builder()
                .canModify(false)
                .cDate(DateUtil.toString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
                .title("기본 디렉토리")
                .icon("el-icon-folder-delete")
                .iconColor("white")
                .fontColor("white")
                .userId(userId)
                .build());

        return defaultData;
    }

    private void defaultCategoryInsert(String userId) {
        List<TodoCategory> entity = defaultTodoCategories(userId)
                .stream()
                .map(t -> {
                    Account account = null;
                    if(!StringUtil.isEmpty(t.getUserId())){
                        account = this.accountRepository.findById(userId).orElse(null);
                    }
                    TodoCategory category = t.toEntity();
                    category.setAccount(account);
                    return category;
                })
                .collect(Collectors.toList());

        this.jpaRepository.saveAll(entity);
    }

    @Override
    @Transactional
    public TodoCategoryDto save(TodoCategoryDto dto) {

        TodoCategory category = dto.toEntity();
        if (dto.getUserId() != null) {
            Account account = this.accountRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException(NOT_FIND_DATA));
            category.setAccount(account);
        }

        return this.saveEntity(category);
    }

    @Override
    public TodoCategoryDto update(Long pk, TodoCategoryDto dto) {
        TodoCategory entity = this.findEntityById(pk);
        entity.patch(dto);
        return new TodoCategoryDto().of(entity);
    }


    public List<TodoCategoryDto> findAll() {
        return this.jpaRepository.findAll()
                .stream()
                .map(t -> new TodoCategoryDto().of(t))
                .collect(Collectors.toList());
    }

    public List<TodoCategoryDto> findAll(String userId) {

//        defaultCategoryInsert(userId);
        return this.jpaRepository.findAll()
                .stream()
                .filter(t -> t.getAccount() != null && t.getAccount().getId().equals(userId))
                .map(t -> new TodoCategoryDto().of(t))
                .collect(Collectors.toList());
    }
}
