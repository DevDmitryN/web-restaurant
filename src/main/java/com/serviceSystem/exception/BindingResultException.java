package com.serviceSystem.exception;

import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class BindingResultException extends RuntimeException {

    private StringBuffer messages = new StringBuffer();

    public BindingResultException(List<ObjectError> errors){
//        List<String> messages = errors.stream().map( error -> error.getDefaultMessage()).collect(Collectors.toList());
        errors.stream().map(objectError -> objectError.getDefaultMessage()).forEach(s -> messages.append(s + "; "));

    }

    @Override
    public String getMessage() {
        return messages.toString();
    }
}
