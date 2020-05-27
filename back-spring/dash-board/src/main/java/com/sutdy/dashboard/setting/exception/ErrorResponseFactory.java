package com.sutdy.dashboard.setting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author kuh
 * @since 2020.05.28
 */
public class ErrorResponseFactory {

    public static ResponseEntity<ErrorResponse> create(HttpStatus status, String msg){

        ErrorResponse error = ErrorResponse.builder()
                .httpStatus(status.value())
                .msg(msg)
                .build();

        return new ResponseEntity<ErrorResponse>(error, status);
    }
}
