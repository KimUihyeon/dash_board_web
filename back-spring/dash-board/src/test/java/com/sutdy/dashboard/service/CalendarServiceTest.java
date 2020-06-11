package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.calendars.TaskTag;
import com.sutdy.dashboard.domain.calendars.TaskTagRepository;
import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.dto.TaskTagDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import org.junit.Assert;
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

    private AccountDto getAccountSample() {
        AccountDto dto = new AccountDto(Account.builder()
                .id(loginId)
                .name("userName")
                .cDate(LocalDateTime.now())
                .build());
        dto.setPw("123123123");
        return dto;
    }

    private Account createSampleData() throws NoSuchAlgorithmException {
        if (this.accountService.findById(loginId) != null) {
            return this.accountService.findById(loginId).toEntity();
        } else {
            return this.accountService.save(getAccountSample()).toEntity();
        }
    }

    @Test
    @Transactional
    public void tagSaveTest() throws NoSuchAlgorithmException {
        //given
        Account account = createSampleData();
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
        Account account = createSampleData();
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
        Account account = createSampleData();
        TaskTagDto dto = TaskTagDto.builder()
                .userId(account.getId())
                .cDate(DateUtil.localDateTimeToString(LocalDateTime.now(), ApplicationStringConfig.DATE_FORMAT))
                .color("#fff")
                .description("test Data")
                .title("테스트 캘린더 테그 생성")
                .build();
        TaskTagDto savedTag = this.calendarService.tagSave(dto);
        TaskTagDto deletedTag =this.calendarService.tagDelete(savedTag.getId());

        //when
        TaskTag findTag = this.taskTagRepository.findById(deletedTag.getId()).orElse(null);


        //then
        Assert.assertEquals(savedTag.getTitle(), dto.getTitle());
        Assert.assertNull(findTag);

    }

    public void tagUpdateTest() {

    }


    public void saveTest() {

    }

    public void getListDate() {

    }


    public void insertTest() {

    }

    public void updateTest() {

    }
}
