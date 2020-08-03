package com.sutdy.dashboard.service;

import com.sutdy.dashboard.domain.system.SystemError;
import com.sutdy.dashboard.domain.system.SystemErrorFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author kuh
 * @since 2020.05.28
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class SystemErrorServiceTest {


    @Autowired
    private SystemErrorService systemErrorService;

    @Test
    public void system_저장_테스트() {
        //given
        SystemError error;
        try {
            throw new IllegalArgumentException("애러나라라아아아");
        } catch (Exception e) {

            error = this.systemErrorService.save(0, e);
        }

        //when
        SystemError findError = this.systemErrorService.findById(error.getId());

        //then
        Assert.assertTrue(findError.getId() == error.getId());
        Assert.assertEquals(findError.getError(), error.getError());
        System.out.println(findError.toString());
    }
}
