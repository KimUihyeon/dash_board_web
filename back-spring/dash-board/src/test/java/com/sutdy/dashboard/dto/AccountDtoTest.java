package com.sutdy.dashboard.dto;


import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.DateUtil;
import com.sutdy.dashboard.setting.util.SecurityStringUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * dto - entity
 * modelMapper Test
 *
 * @author kuh
 * @since 08.20.05
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AccountDtoTest {

    private final Logger logger = LoggerFactory.getLogger(AccountDtoTest.class);

    @Test
    public void AccountDto_엔티티_컨버팅_테스트() {
        //given
        String pw = "";
        String cDate = DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT);
        logger.info(cDate);
        try {
            pw = SecurityStringUtil.encryptMD5("123123");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        AccountDto dto = new AccountDto();
        dto.setId("accountDtoTest@naver.com");
        dto.setName("테스트 이름");
        dto.setCDate(cDate);
        dto.setPw(pw);

        //when
        Account account = dto.toEntity();

        //then
        Assert.assertEquals(account.getId(), dto.getId());
        Assert.assertEquals(account.getName(), dto.getName());
        Assert.assertEquals(account.getId(), dto.getId());
        Assert.assertEquals(account.getPw(), dto.getPw());
        Assert.assertEquals
                (DateUtil.localDateTimeToString(
                        account.getCDate(), ApplicationStringConfig.DATE_FORMAT
                ), dto.getCDate());

    }

    @Test
    public void AccountDto_컨버팅_테스트() {
        //given
        String pw = "";
        String cDate = DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT);
        logger.info(cDate);
        try {
            pw = SecurityStringUtil.encryptMD5("123123");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        Account account = Account.builder()
                .id("accountDtoTest@naver.com")
                .pw(pw)
                .name("테스트 이름")
                .cDate(DateUtil.now())
                .build();

        //when
        AccountDto dto = new AccountDto().of(account);

        //then
        Assert.assertEquals(account.getId(), dto.getId());
        Assert.assertEquals(account.getName(), dto.getName());
        Assert.assertEquals(account.getId(), dto.getId());
        Assert.assertEquals(account.getPw(), dto.getPw());
        Assert.assertEquals
                (DateUtil.localDateTimeToString(
                        account.getCDate(), ApplicationStringConfig.DATE_FORMAT
                ), dto.getCDate());

    }

    @Test
    public void AccountDto_연쇄_컨버팅_테스트() {

        //given
        String pw = "";
        String cDate = DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT);
        logger.info(cDate);
        try {
            pw = SecurityStringUtil.encryptMD5("123123");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        AccountDto dto = new AccountDto();
        dto.setId("accountDtoTest@naver.com");
        dto.setName("테스트 이름");
        dto.setCDate(cDate);
        dto.setPw(pw);

        //when
        Account account = dto.toEntity();
        AccountDto reconvertAccountDto = new AccountDto().of(account);

        //then
        Assert.assertEquals(reconvertAccountDto.getId(), dto.getId());
        Assert.assertEquals(reconvertAccountDto.getName(), dto.getName());
        Assert.assertEquals(reconvertAccountDto.getId(), dto.getId());
        Assert.assertEquals(reconvertAccountDto.getPw(), dto.getPw());
        Assert.assertEquals(reconvertAccountDto.getCDate(),dto.getCDate());
    }
}
