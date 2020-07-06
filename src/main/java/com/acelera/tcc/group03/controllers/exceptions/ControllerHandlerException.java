package com.acelera.tcc.group03.controllers.exceptions;

import com.acelera.tcc.group03.exceptions.CustomerAccountNotFound;
import com.acelera.tcc.group03.exceptions.TransactionChannelNotFound;
import com.acelera.tcc.group03.exceptions.TransactionTypeNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerHandlerException {

    @ExceptionHandler(CustomerAccountNotFound.class)
    public ResponseEntity<StandardError> objectNotFound(CustomerAccountNotFound e, HttpServletRequest request) {

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

    }

    @ExceptionHandler(TransactionChannelNotFound.class)
    public ResponseEntity<StandardError> transactionChannelNotFound(TransactionChannelNotFound e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

    }

    @ExceptionHandler(TransactionTypeNotFound.class)
    public ResponseEntity<StandardError> transactionTypeNotFound(TransactionTypeNotFound e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

    }


}

