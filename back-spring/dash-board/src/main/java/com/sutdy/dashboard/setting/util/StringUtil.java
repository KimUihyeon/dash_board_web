package com.sutdy.dashboard.setting.util;

/**
 * @author kuh
 * @since 2020.08.12
 */
public class StringUtil {

    public static boolean isEmpty(String str){
        if(str == null || str.length() == 0){
            return true;
        }
        return false;
    }
}