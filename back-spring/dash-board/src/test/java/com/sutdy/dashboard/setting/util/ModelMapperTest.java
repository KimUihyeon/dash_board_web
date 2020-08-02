package com.sutdy.dashboard.setting.util;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.dto.AccountDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.08.02
 * @description
 * Model Mapper lib Test Class
 *
 * https://mvnrepository.com/artifact/org.modelmapper/modelmapper/2.3.2
 * https://blog.naver.com/writer0713/221596629064
 * https://baek.dev/post/15/
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ModelMapperTest {


    /**
     * @name modelMapper_기본맵핑_테스트
     * @type 단위테스트
     * @since 20.08.02
     *
     * @description
     * entity - DTO 프로퍼티가 같은 객체 맵핑해보기
     */
    @Test
    public void modelMapper_기본맵핑_테스트(){
        // given
        Account account = Account.builder()
                .name("테스트 네임")
                .pw("123123123")
                .cDate(LocalDateTime.now())
                .id("test@naver.com")
                .build();
        ModelMapper modelMapper = new ModelMapper();


        //when
        AccountDto dto = modelMapper.map(account, AccountDto.class);

        //then

        Assert.assertEquals(dto.getName(), account.getName());
        Assert.assertEquals(dto.getId(), account.getId());
        Assert.assertEquals(dto.getPw(), account.getPw());
        Assert.assertEquals(dto.getCDate(), account.getCDate());
    }
}