package com.sutdy.dashboard.setting.exception.impl;

/**
 * JwtTimeoutException
 *
 * @author Kimuihyeon
 * @since 2020.11.10
 */
public class JwtTimeoutException extends Exception{
    public JwtTimeoutException() {
        super();
    }
    public JwtTimeoutException(String msg) {
        super(msg);
    }

    public JwtTimeoutException(String msg, Throwable cause) {
        super(msg, cause);
    }
}