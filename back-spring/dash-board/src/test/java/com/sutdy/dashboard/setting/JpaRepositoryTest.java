package com.sutdy.dashboard.setting;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.members.AccountRepository;
import com.sutdy.dashboard.domain.todo.TodoCategory;
import com.sutdy.dashboard.domain.todo.TodoCategoryRepository;
import com.sutdy.dashboard.domain.todo.TodoRepository;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kuh
 * @since 2020.07.31
 * <p>
 * Jap 관련 스터디 검증용 로직
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaRepositoryTest {

    @Autowired
    private TodoCategoryRepository todoCategoryRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private AccountRepository accountRepository;

    private final Logger logger = LoggerFactory.getLogger(JpaRepositoryTest.class);

    @Test
    @Transactional
    public void jpa_벌크_인서트_테스트() {

        //given
        short testCase = 10;
        List<Account> accountList = new ArrayList<>();
        for (int i = 0; i < testCase; i++) {
            Account account = Account.builder()
                    .cDate(DateUtil.now())
                    .id("insertTest id_ " + i)
                    .pw("insertTest Pw _ " + i)
                    .name("insertTest Name_")
                    .build();
            accountList.add(account);
        }

        //when
        logger.info("------bulk  insert before--------");
        logger.info("------bulk  insert before--------");
        logger.info("------bulk  insert before--------");
        this.accountRepository.saveAll(accountList);
        // 이거 batchInsert 아님 ..!
        // 참고 ->
        // https://homoefficio.github.io/2020/01/25/Spring-Data%EC%97%90%EC%84%9C-Batch-Insert-%EC%B5%9C%EC%A0%81%ED%99%94/
        logger.info("------bulk  insert after--------");
        logger.info("------bulk  insert after--------");
        logger.info("------bulk  insert after--------");
    }


    @Test
    public void jpa_조인_엔티티_세이브_테스트() {
        //given
        String accountId = "테스트중입니다@테스트.com";
        deleteAccount(accountId);

        Account account = Account.builder()
                .cDate(DateUtil.now())
                .id(accountId)
                .pw("insertTest Pw _")
                .name("insertTest Name_")
                .build();

        TodoCategory todoCategory = TodoCategory.builder()
                .cDate(DateUtil.now())
                .title("test Title")
                .iconColor("test Color")
                .account(account)
                .build();

        //when
        /**
         * case1
         * 1. Account Not insert
         * 2. TodoCategory insert
         * 아래와 같은 애러가 발생함
         * 소스코드 ->
         //        long todoCategoryId = this.todoCategoryRepository.save(todoCategory).getId();
         //        Account savedAccount = this.accountRepository.findById(accountId).orElse(null);
         *
         *
         * object references an unsaved transient instance 발생함 .
         */

        ////
        /**
         * case2
         * 1. Account insert
         * 2. todocategory insert
         */
        this.accountRepository.save(account); // 선행되야함.
        long todoCategoryId = this.todoCategoryRepository.save(todoCategory).getId();
        Account savedAccount = this.accountRepository.findById(accountId).orElse(null);


        //then
        deleteTodoCategory(todoCategoryId);
        deleteAccount(accountId);
    }

    private void deleteTodoCategory(long id) {
        try {
            this.todoCategoryRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

    private void deleteAccount(String id) {
        try {
            this.accountRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }


    @Test
    @Transactional
    @Rollback(true)
    public void jpa_조인_타이밍_테스트() {
        String accountId = "조인타이밍테스트@테스트.com";
        deleteAccount(accountId);

        Account account = Account.builder()
                .cDate(DateUtil.now())
                .id(accountId)
                .pw("insertTest Pw _")
                .name("insertTest Name_")
                .build();

        TodoCategory todoCategory = TodoCategory.builder()
                .cDate(DateUtil.now())
                .title("test Title")
                .iconColor("test Color")
                .account(account)
                .build();
        this.accountRepository.save(account); // 선행되야함.
        long todoCategoryId = this.todoCategoryRepository.save(todoCategory).getId();
        logger.info("----------- select query 호출 전 ");
        logger.info("----------- select query 호출 전 ");
        logger.info("----------- select query 호출 전 ");
        TodoCategory savedTodoCategory = this.todoCategoryRepository.findById(todoCategoryId).orElse(null);
        logger.info("----------- select query 호출 후 | getAccount 이전");
        logger.info("----------- select query 호출 후 | getAccount 이전");
        logger.info("----------- select query 호출 후 | getAccount 이전");

        /**
         * Many To one 기본 fetch 전략은 EAGER 이다.
         * EAGER -> 즉시로딩
         *      부모 객체 셀렉트시 객체 다 조인해서 끌고옴
         *
         *  TodoCategory savedTodoCategory = this.todoCategoryRepository.findById(todoCategoryId).orElse(null);
         *  실행시 이미 조인쿼리로 TodoCategory에 관계가 있는 객체까지 다 불러온 상태다 .
         *  따라서 Account todoRelation = savedTodoCategory.getAccount(); 하면
         *  Account를 얻을수 있다.
         *
         * LAZY -> 지연로딩
         *      필요한 시점에 getEntity().getClass()로 해야 끌고옴;
         *
         *  TodoCategory savedTodoCategory = this.todoCategoryRepository.findById(todoCategoryId).orElse(null);
         *  상태일떄 TodoCategory 만 불러오고 Account가 필요한 시점엔
         *  Account todoRelation = savedTodoCategory.getAccount().getClass(); 해야 해당 객체를 얻을수 있다.
         *
         */

        Account todoRelation = savedTodoCategory.getAccount();
        todoRelation.getCDate();
        AccountDto dto = new AccountDto().of(todoRelation);
        System.out.println(dto.toString());
        logger.info("----------- getAccount이후");
        logger.info("----------- getAccount이후");
        logger.info("----------- getAccount이후");

        deleteAccount(accountId);
        deleteTodoCategory(savedTodoCategory.getId());
    }

}
