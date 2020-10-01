package com.sutdy.dashboard.setting.util;

import com.sutdy.dashboard.domain.members.Account;
import com.sutdy.dashboard.domain.todo.Todo;
import com.sutdy.dashboard.dto.AccountDto;
import com.sutdy.dashboard.dto.AccountDtoTest;
import com.sutdy.dashboard.dto.TodoDto;
import com.sutdy.dashboard.setting.ApplicationStringConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @description Model Mapper lib Test Class
 * <p>
 * https://mvnrepository.com/artifact/org.modelmapper/modelmapper/2.3.2
 * https://blog.naver.com/writer0713/221596629064
 * https://baek.dev/post/15/
 * @since 2020.08.02
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ModelMapperTest {


    /**
     * @name modelMapper_기본맵핑_테스트
     * @type 단위테스트
     * @description entity - DTO 프로퍼티가 같은 객체 맵핑해보기
     * @since 20.08.02
     */
    @Test
    public void modelMapper_기본맵핑_테스트() {
        // given
        Account account = Account.builder()
                .name("테스트 네임")
                .pw("123123123")
                .cDate(DateUtil.now())
                .id("test@naver.com")
                .build();
        ModelMapper modelMapper = new ModelMapper();


        //when
        AccountDto dto = modelMapper.map(account, AccountDto.class);
        dto.setCDate(DateUtil.localDateTimeToString(account.getCDate(), ApplicationStringConfig.DATE_FORMAT));

        //then

        Assert.assertEquals(dto.getName(), account.getName());
        Assert.assertEquals(dto.getId(), account.getId());
        Assert.assertEquals(dto.getPw(), account.getPw());
        Assert.assertEquals(dto.getCDate(), DateUtil.localDateTimeToString(account.getCDate(), ApplicationStringConfig.DATE_FORMAT));
    }


    /**
     * @name modelMapper_기본맵핑_테스트
     * @type 단위테스트
     * @description DTO - entity  프로퍼티가 같은 객체 맵핑해보기
     * @since 20.08.02
     */
    @Test
    public void modelMapper_기본맵핑_역방향_테스트() {
        /**
         * ModelMapper
         * -> 리플렉션 돌면서 Setter에 필드의 유사도로 데이터를 넣어주는 라이브러리.
         *
         * Entity 객체는 Setter가 없으므로 맵핑이 안된다.
         *
         * 따라서
         * Entity -> dto는 ModelMapper
         * dto -> Entity는 수동으로 맵핑해주도록 하자. (dto에 toEntity 로직 만들기);
         *
         */

        // given
//        AccountDto dto = new AccountDto();
//        dto.setName("테스트 네임");
//        dto.setPw("123123123");
//        dto.setCDate(DateUtil.now());
//        dto.setId("test@naver.com");
//        ModelMapper modelMapper = new ModelMapper();


        //when
//        Account account = modelMapper.map(dto, Account.class);

        //then

//        Assert.assertEquals(dto.getName(), account.getName());
//        Assert.assertEquals(dto.getId(), account.getId());
//        Assert.assertEquals(dto.getPw(), account.getPw());
//        Assert.assertEquals(dto.getCDate(), account.getCDate());
    }

    @Test
    public void modelConverter_기본맵핑_테스트(){

        TodoDto dto = TodoDto.builder()
                .date(DateUtil.localDateTimeToString(DateUtil.now(), ApplicationStringConfig.DATE_FORMAT))
                .memo("memo test Logic")
                .title("title test Logic")
                .build();


        //when
        Todo todo = dto.toEntity();
        TodoDto convertedDto = new TodoDto().of(todo);

        //then

        Assert.assertEquals(todo.getTitle(), convertedDto.getTitle());
        Assert.assertEquals(todo.getId(), convertedDto.getId());
        Assert.assertEquals(todo.getContents(), convertedDto.getMemo());
    }
}