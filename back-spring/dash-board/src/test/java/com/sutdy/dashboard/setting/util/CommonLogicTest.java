package com.sutdy.dashboard.setting.util;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;

/**
 * @author kuh
 * @since 2020.11.10
 */

@SpringBootTest
public class CommonLogicTest {


    @Test
    public void HttpMethod_Test(){

        String httpOptions = HttpMethod.OPTIONS.name();

        String stringOptions = "OPTIONS";


        Assert.assertEquals(httpOptions, stringOptions);
    }
}