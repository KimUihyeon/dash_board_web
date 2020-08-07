package com.sutdy.dashboard.setting.exception.impl;

public class JwtTimeoutException extends Exception {

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
