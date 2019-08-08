package com.serviceSystem.controller;

import com.serviceSystem.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {NoSuchItemException.class, ActiveOrderNotFoundException.class})
    public ResponseEntity handleNotFoundException(RuntimeException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage(),httpStatus), httpStatus);
    }
    @ExceptionHandler(value = {BadCredentialsException.class, NotCorrespondingIdException.class, OrderAlreadyTakenException.class})
    public ResponseEntity handleBadRequestException(RuntimeException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage(),httpStatus),httpStatus);
    }

}
