package com.sutdy.dashboard.setting.util;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.members.AccountRepository;
import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.domain.todo.TodoCategoryRepository;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.dto.TodoCategoryDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TempDataFactory {

    private static Logger logger = LoggerFactory.getLogger(TempDataFactory.class);

    @Autowired
    private TodoCategoryRepository todoCategoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TodoRepository todoRepository;


    public void createTodoDatas() {

        logger.info("createTodoDatas 생성 --------------->");

        Account saveAccount = null;
        try{
            String securityPw = SecurityStringUtil.encryptSHA256("123123123");
            Account account = Account.builder()
                    .id("test@naver.com")
                    .pw(securityPw )
                    .name("김의현")
                    .build();
            saveAccount = this.accountRepository.save(account);
        }catch (Exception e){
            logger.error(e.getStackTrace().toString());
        }

        TodoCategoryDto categoryDto = TodoCategoryDto.builder()
                .canModify(false)
                .cDate(DateUtil.toString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
                .title("테스트 디렉토리 2")
                .icon("el-icon-folder-delete")
                .iconColor("white")
                .fontColor("white")
                .build();
        TodoCategory category = categoryDto.toEntity();
        category.setAccount(saveAccount);
        TodoCategory saveCategory = this.todoCategoryRepository.save(category);



        for (int i = 0; i < 10; i++) {
            TodoCategory relationCategory = null;

            if (i % 2 == 0) {
                relationCategory = saveCategory;
            }

            Todo todo = Todo.builder()
                    .cDate(DateUtil.now())
                    .id(Long.parseLong(String.valueOf(i)))
                    .title("제목 _" + i)
                    .contents("메모 _ " + i)
                    .todoCategory(relationCategory)
                    .build();

            todoRepository.save(todo);
        }
    }

}
