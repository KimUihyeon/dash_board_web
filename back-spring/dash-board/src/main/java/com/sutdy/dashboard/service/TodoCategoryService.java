package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.members.AccountRepository;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.domain.todo.TodoCategoryRepository;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.service.common.BaseCrudService;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.data.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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


        // !테스트 코드
        defaultCategoryInsert("admin@naver.com");
    }


    public List<TodoCategoryDto> defaultTodoCategories(String userId) {

        List<TodoCategoryDto> defaultData = new ArrayList<>();
        defaultData.add(TodoCategoryDto.builder()
                .canModify(false)
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
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
                .map(t -> t.toEntity())
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
        return new TodoCategoryDto(entity);
    }


    public List<TodoCategoryDto> findAll() {
        return this.jpaRepository.findAll()
                .stream()
                .map(t -> new TodoCategoryDto(t))
                .collect(Collectors.toList());
    }

    public List<TodoCategoryDto> findAll(String userId) {
        return this.jpaRepository.findAll()
                .stream()
                .filter(t -> t.getAccount() != null && t.getAccount().getId().equals(userId))
                .map(t -> new TodoCategoryDto(t))
                .collect(Collectors.toList());
    }
}
