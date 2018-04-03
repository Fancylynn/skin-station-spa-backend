package com.fancylynn.skinstationspa.exception;

/**
 * Created by Lynn on 2018/4/2.
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
