package com.sutdy.dashboard.setting.exception;

import com.sutdy.dashboard.service.SystemErrorService;
import com.sutdy.dashboard.setting.exception.impl.JwtAuthException;
import com.sutdy.dashboard.setting.exception.impl.JwtTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.AccessException;

/**
 * @author kuh
 * @since 2020.05.10
 */

@ControllerAdvice
public class ExceptionAdvice {

    @Autowired
    private SystemErrorService systemErrorService;


    public ExceptionAdvice(){
        System.out.println("ExceptionAdvice resit");
    }

    /**
     * 권한 애러 (아직 미구현 ..!)
     *
     * @param e
     */
    @ExceptionHandler(AccessException.class)
    public ResponseEntity<ErrorResponse> AccessExceptionHandle(AccessException e) {
        systemErrorService.save(0, e);
        return ErrorResponseFactory.create(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    /**
     * Null Pointer Error
     *
     * @param e NullPointerException Error (객체 없엉 ... !)
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> NullPointerExceptionHandle(NullPointerException e) {

        systemErrorService.save(0, e);
        return ErrorResponseFactory.create(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    /**
     * Jpa select 절에서 데이터 못찾으면 해당 에러 발생시킴.!
     * 파라미터 불충분 애러 !
     *
     * @param e IllegalArgumentException Error
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> IllegalArgumentExceptionHandle(IllegalArgumentException e) {

        systemErrorService.save(0, e);
        return ErrorResponseFactory.create(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    /**
     * 정의되지 않은 http method 요청시 발생하는 Exception 핸들
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> requestMethodNotSupportedHandle(HttpRequestMethodNotSupportedException e) {

        systemErrorService.save(0, e);
        return ErrorResponseFactory.create(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    /**
     * 미구현 인터페이스 요청시 발생하는 Exception
     *
     * @param e NotImplementedException
     * @return
     */
    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<ErrorResponse> notImplementedMethodExceptionHandle(NotImplementedException e) {

        systemErrorService.save(0, e);
        return ErrorResponseFactory.create(HttpStatus.BAD_REQUEST, e.getMessage());
    }


    /**
     * TODO : 검증 필요
     * JwtTimeoutException
     * Jwt 인증시 인증서 만료 Exception을 발생하기 위함.
     *
     * @param e JwtTimeoutException
     * @return
     */
    @ExceptionHandler(JwtAuthException.class)
    public ResponseEntity<ErrorResponse> jwtAuthExceptionHandle(JwtAuthException e){

        systemErrorService.save(0, e);
        return ErrorResponseFactory.create(HttpStatus.UNAUTHORIZED, e.getMessage());
    }


    /**
     * TODO : 검증 필요
     * JwtTimeoutException
     * Jwt 인증시 인증서 만료 Exception을 발생하기 위함.
     *
     * @param e JwtTimeoutException
     * @return
     */
    @ExceptionHandler(JwtTimeoutException.class)
    public ResponseEntity<ErrorResponse> jwtTimeoutExceptionHandle(JwtAuthException e){

        systemErrorService.save(0, e);
        return ErrorResponseFactory.create(HttpStatus.UNAUTHORIZED, e.getMessage());
    }


    /**
     * OutOfMemoryException 외에
     * 정의되지 않은 Exception을 처리하기 위한 핸들
     *
     * @param e Exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> commonExceptionHandle(Exception e) {

        systemErrorService.save(0, e);
        return ErrorResponseFactory.create(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }



}
