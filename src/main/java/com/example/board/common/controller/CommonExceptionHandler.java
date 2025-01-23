package com.example.board.common.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(EntityExistsException.class)
    public String entityNotfound(EntityNotFoundException e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "common/error_page";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgument(IllegalArgumentException e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "common/error_page";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validException(MethodArgumentNotValidException e, Model model){
        model.addAttribute("errorMessage", e.getMessage());
        return "common/errer_page";
    }
}
