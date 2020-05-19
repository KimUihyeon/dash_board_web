package com.sutdy.dashboard.setting.util;


import com.sutdy.dashboard.setting.ApplicationStringConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class EncrytionTest {

    @Test
    public void md5Test() throws NoSuchAlgorithmException {
        String str = "hello world";
        String securityString = SecurityStringUtil.encryptMD5(str);

        System.out.println(securityString);
        Assert.assertTrue(!securityString.isEmpty());
    }


    @Test
    public void SHA256Test() throws NoSuchAlgorithmException {
        String str = "hello world";
        String securityString = SecurityStringUtil.encryptSHA256(str);

        System.out.println(securityString);
        Assert.assertTrue(!securityString.isEmpty());
    }


    @Test
    public void AES256Test() throws Exception {

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
