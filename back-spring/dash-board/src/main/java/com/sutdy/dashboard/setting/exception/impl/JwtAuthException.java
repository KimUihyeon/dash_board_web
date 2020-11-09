package com.sutdy.dashboard.setting.exception.impl;

public class JwtAuthException extends Exception {

    public JwtAuthException() {
        super();
    }

    public JwtAuthException(String msg) {
        super(msg);
    }

    public JwtAuthException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
