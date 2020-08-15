/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author louie
 */
@ControllerAdvice
@RestController
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlException(SQLIntegrityConstraintViolationException ex, WebRequest request) {
        final String CONSTRAINT_MESSAGE = "Could not save your item. "
                + "Please ensure it is valid and try again.";

        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public final ResponseEntity<Error> handleResultNotFoundException(EmptyResultDataAccessException ex, WebRequest request) {

        final String MESSAGE = "Game Not Found";

        Error err = new Error();
        err.setMessage(MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectResultSizeDataAccessException.class)
    public final ResponseEntity<Error> handleIncorrectSizeException(IncorrectResultSizeDataAccessException ex, WebRequest request) {

        final String MESSAGE = "Something went wrong, please try again";

        Error err = new Error();
        err.setMessage(MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
