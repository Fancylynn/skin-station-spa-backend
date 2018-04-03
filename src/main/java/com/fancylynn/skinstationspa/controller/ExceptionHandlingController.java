package com.fancylynn.skinstationspa.controller;

import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolationException;

/**
 * Created by Lynn on 2018/3/7.
 */

@ControllerAdvice
public class ExceptionHandlingController {
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ConstraintViolationException.class,
            DataException.class,
            EntityExistsException.class,
            BadCredentialsException.class})
    public ResponseEntity<String> badRequestError(Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            AuthenticationException.class,
    })
    public ResponseEntity<String> unauthorizedError(Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}