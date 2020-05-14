package com.sutdy.dashboard.common;

import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import io.jsonwebtoken.Jwt;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * @author kuh
 * @since 2020.05.10
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTTest {

    @Test
    public void createJWT() {

        String id = "test@naver.com";
        String name = "김의현";

        String jwt = JWT.create(id, name, 3);
        System.out.println(jwt);


        Assert.assertTrue(!jwt.isEmpty());
    }


    @Test
    public void authJWT() {

        // given
        String id = "test@naver.com";
        String name = "김의현";


        String jwt = JWT.create(id, name, 3);

        // when

        AuthResponse result = JWT.authJwt(jwt);

        // then
        Assert.assertTrue(result.getAuthType() == AuthEnum.Auth);
    }

}
