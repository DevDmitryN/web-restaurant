package com.serviceSystem.controller;

import com.serviceSystem.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerController {


    @ExceptionHandler(value = {NoSuchItemException.class, ActiveOrderNotFoundException.class})
    public ResponseEntity handleNotFoundException(RuntimeException e){
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage(),NOT_FOUND), NOT_FOUND);
    }
    @ExceptionHandler(value = {BadCredentialsException.class, NotCorrespondingIdException.class, OrderAlreadyTakenException.class})
    public ResponseEntity handleBadRequestException(RuntimeException e){
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessage(),BAD_REQUEST),BAD_REQUEST);
    }
    @ExceptionHandler(value = {BindingResultException.class})
    public ResponseEntity handleBindingResultException(BindingResultException e){
        return new ResponseEntity<>(new ExceptionWrapper(e.getMessages(), BAD_REQUEST), BAD_REQUEST);
    }

}
