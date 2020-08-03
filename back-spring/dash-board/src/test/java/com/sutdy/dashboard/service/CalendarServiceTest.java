package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.calendars.TaskTag;
import com.sutdy.dashboard.domain.calendars.TaskTagRepository;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.dto.AccountDtoTest;
import com.sutdy.dashboard.dto.TaskTagDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuh
 * @since 2020.06.11
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CalendarServiceTest {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private TaskTagRepository taskTagRepository;

    @Autowired
    private AccountService accountService;


    private String loginId = "smaple_account1@naver.com";
    private Long tempTaskTagId;


    @Before
    public void createSampleData() throws NoSuchAlgorithmException {

        AccountDto accountDto = null;

        if (this.accountService.findById(loginId) != null) {
            accountDto = this.accountService.findById(loginId);
        } else {

            AccountDto dto = new AccountDto(Account.builder()
                    .id(loginId)
                    .name("userName")
                    .cDate(LocalDateTime.now())
                    .build());
            dto.setPw("123123123");

            accountDto = this.accountService.save(dto);
        }

        TaskTagDto dto = TaskTagDto.builder()
                .userId(accountDto.getId())
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .description("test Data")
                .title("테스트 캘린더 테그 생성")
                .build();

        this.tempTaskTagId = this.calendarService.tagSave(dto).getId();
    }

    @After
    public void cleanup(){
        this.accountService.delete(loginId);
        this.taskTagRepository.deleteById(tempTaskTagId);
    }


    @Test
    @Transactional
    public void tagSaveTest() throws NoSuchAlgorithmException {
        //given
        Account account = this.accountService.findById(loginId).toEntity();
        TaskTagDto dto = TaskTagDto.builder()
                .userId(account.getId())
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .description("test Data")
                .title("테스트 캘린더 테그 생성")
                .build();

        //when
        TaskTagDto savedTag = this.calendarService.tagSave(dto);

        //then
        Assert.assertEquals(savedTag.getTitle(), dto.getTitle());

    }


    @Test
    @Transactional
    public void tagListTest() throws NoSuchAlgorithmException {
        //given
        Account account = this.accountService.findById(loginId).toEntity();
        List<TaskTagDto> taskTags = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            TaskTagDto dto = TaskTagDto.builder()
                    .userId(account.getId())
                    .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                    .color("#fff")
                    .description("test Data " + i)
                    .title("테스트 캘린더 테그 생성 " + i)
                    .build();
            TaskTagDto savedTag = this.calendarService.tagSave(dto);
            taskTags.add(savedTag);
        }

        List<Long> ids = taskTags.stream().map(t -> t.getId()).collect(Collectors.toList());

        //when
        List<TaskTagDto> findAll = this.calendarService.tagFindAllById(ids);

        //then
        Assert.assertNotNull(findAll);
        Assert.assertEquals(findAll.size(), taskTags.size());

        Assert.assertEquals(
                findAll.stream()
                        .filter(t -> t.getId() == ids.get(0)).collect(Collectors.toList())
                        .get(0)
                        .getTitle(),
                taskTags.stream()
                        .filter(t -> t.getId() == ids.get(0)).collect(Collectors.toList())
                        .get(0)
                        .getTitle()
        );


        Assert.assertEquals(
                findAll.stream()
                        .filter(t -> t.getId() == ids.get(ids.size() - 1)).collect(Collectors.toList())
                        .get(0)
                        .getTitle(),
                taskTags.stream()
                        .filter(t -> t.getId() == ids.get(ids.size() - 1)).collect(Collectors.toList())
                        .get(0)
                        .getTitle()
        );


    }

    @Test
    @Transactional
    public void tagDeleteTest() throws NoSuchAlgorithmException {
        //given
        Account account = this.accountService.findById(loginId).toEntity();
        TaskTagDto dto = TaskTagDto.builder()
                .userId(account.getId())
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .description("test Data")
                .title("테스트 캘린더 테그 생성")
                .build();
        TaskTagDto savedTag = this.calendarService.tagSave(dto);
        TaskTagDto deletedTag = this.calendarService.tagDelete(savedTag.getId());

        //when
        TaskTag findTag = this.taskTagRepository.findById(deletedTag.getId()).orElse(null);


        //then
        Assert.assertEquals(savedTag.getTitle(), dto.getTitle());
        Assert.assertNull(findTag);

    }

    @Test
    @Transactional
    public void tagUpdateTest() throws NoSuchAlgorithmException {
        // given
        Account account = this.accountService.findById(loginId).toEntity();
        TaskTagDto dto = TaskTagDto.builder()
                .userId(account.getId())
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .description("test Data")
                .title("테스트 캘린더 테그 생성")
                .build();
        TaskTagDto savedTag = this.calendarService.tagSave(dto);
        String changeTitle = "변경된 타이틀 !";
        savedTag.setTitle(changeTitle);

        //when
        TaskTagDto updatedDto = this.calendarService.tagUpdate(savedTag);

        //then
        Assert.assertEquals(changeTitle, updatedDto.getTitle());
        Assert.assertEquals(updatedDto.getTitle(), savedTag.getTitle());
    }
}
