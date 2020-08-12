package com.sutdy.dashboard.setting.util.auth;

/**
 * @author kuh
 * @since 2020.05.10
 */
public enum AuthEnum {

    NoAuth, // 비 인가
    Auth,   // 인가
    TimeOut, // 인증서 만료
    WrongEncounter // 잘못된 접근 // 삭제된계정
}
