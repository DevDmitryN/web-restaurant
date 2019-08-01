package com.serviceSystem.controller;

import com.serviceSystem.exception.ExceptionWrapper;
import com.serviceSystem.exception.NoSuchItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {NoSuchItemException.class})
    public ResponseEntity<Object> handleNotFoundException(NoSuchItemException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage(),httpStatus), httpStatus);
    }
}
