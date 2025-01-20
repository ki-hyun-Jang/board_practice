package com.example.board.common.controller;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(EntityExistsException.class)
    public String entityNotfound(EntityNotFoundException e){
        return null;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgument(IllegalArgumentException e){
        return null;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validException(MethodArgumentNotValidException e){
        return null;
    }
}
