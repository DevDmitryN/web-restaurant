package com.serviceSystem.controller;

import com.serviceSystem.exception.ExceptionWrapper;
import com.serviceSystem.exception.NoSuchItemException;
import com.serviceSystem.exception.NotCorrespondingIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {NoSuchItemException.class})
    public ResponseEntity handleNotFoundException(NoSuchItemException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage(),httpStatus), httpStatus);
    }
    @ExceptionHandler(value = {BadCredentialsException.class, NotCorrespondingIdException.class})
    public ResponseEntity handleBadRequestException(RuntimeException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage(),httpStatus),httpStatus);
    }
}
