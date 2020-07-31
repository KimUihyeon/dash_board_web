package com.sutdy.dashboard.domain.system;

import java.time.LocalDateTime;

/**
 * @author kuh
 * @since 2020.05.28
 */
public class SystemErrorFactory {

    public static SystemError create(Exception e){
        return  create(null , e);
    }

    public static SystemError create(String userId, Exception e){

        if(userId == null){
            userId = "system";
        }

        try{
            StackTraceElement st = e.getStackTrace()[0];


            return SystemError.builder()
                    .error(e.toString())
                    .declaringClass(st.getClassName())
                    .fileName(st.getFileName())
                    .lineNumber(st.getLineNumber())
                    .methodName(st.getMethodName())
                    .userId(userId)
                    .cDate(LocalDateTime.now())
                    .build();
        }catch (Exception processException){

            StackTraceElement st = processException.getStackTrace()[0];
            return SystemError.builder()
                    .error("SystemErrorFactory 애서 애러남 ..")
                    .declaringClass(st.getClassName())
                    .fileName(st.getFileName())
                    .lineNumber(st.getLineNumber())
                    .methodName(st.getMethodName())
                    .cDate(LocalDateTime.now())
                    .build();
        }
    }
}