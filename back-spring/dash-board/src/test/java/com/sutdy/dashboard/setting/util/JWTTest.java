package com.sutdy.dashboard.setting.util;

import com.sutdy.dashboard.setting.ApplicationStringConfig;
import com.sutdy.dashboard.setting.util.auth.AuthEnum;
import com.sutdy.dashboard.setting.util.auth.AuthResponse;
import com.sutdy.dashboard.setting.util.auth.jwt.JWT;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author kuh
 * @since 2020.05.10
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class JWTTest {

    @Test
    public void webUrlTest(){
        System.out.println(ApplicationStringConfig.WEB_URL);
    }

    @Test
    public void jwt_생성_테스트() {
        
        // given
        String id = "admin@naver.com";
        String name = "김의현";

        String jwt = JWT.createToken(id, name, 3);
        System.out.println(jwt);


        Assert.assertTrue(!jwt.isEmpty());
    }


    @Test
    public void jwt_인증테스트() {

        // given
        String id = "admin@naver.com";
        String name = "김의현";


        String jwt = JWT.createToken(id, name, 3);

        // when
        AuthResponse result = JWT.auth(jwt);

        // then
        Assert.assertTrue(result.getAuthType() == AuthEnum.Auth);
    }

}
