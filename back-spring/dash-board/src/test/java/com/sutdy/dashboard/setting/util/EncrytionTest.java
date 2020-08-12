package com.sutdy.dashboard.setting.util;


import com.sutdy.dashboard.setting.ApplicationStringConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class EncrytionTest {

    @Test
    public void md5_기능테스트() throws NoSuchAlgorithmException {
        // given
        String str = "hello world";
        //when
        String securityString = SecurityStringUtil.encryptMD5(str);

        //then
        System.out.println(securityString);
        Assert.assertTrue(!StringUtil.isEmpty(securityString));
    }


    @Test
    public void SHA256_기능_테스트() throws NoSuchAlgorithmException {
        // given
        String str = "hello world";
        //when
        String securityString = SecurityStringUtil.encryptSHA256(str);

        //then
        System.out.println(securityString);
        Assert.assertTrue(!StringUtil.isEmpty(securityString));
    }


    @Test
    public void AES256_기능_테스트() throws Exception {

        // given
        String str = "hello world";
        String securityString = SecurityStringUtil.encryptAES256(str, ApplicationStringConfig.STRING_ENCRYPTION_KEY);

        //when
        String decryptedString = SecurityStringUtil.decryptAES256(securityString, ApplicationStringConfig.STRING_ENCRYPTION_KEY);

        //then
        System.out.println(str);
        System.out.println(securityString);
        System.out.println(decryptedString);
        Assert.assertTrue(str.equals(decryptedString));
    }
}
