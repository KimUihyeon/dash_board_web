package com.sutdy.dashboard.setting.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.AccessException;

/**
 * @author kuh
 * @since 2020.05.10
 */

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(AccessException.class)
    public void Test(AccessException exception){

    }
}
