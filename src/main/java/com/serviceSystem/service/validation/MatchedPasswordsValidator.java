package com.serviceSystem.service.validation;

import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.entity.dto.UserDto;
import com.serviceSystem.entity.dto.form.SignUpClientForm;
import com.serviceSystem.entity.dto.form.SignUpWorkerForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchedPasswordsValidator implements ConstraintValidator<MatchedPasswords, UserDto> {
    @Override
    public boolean isValid(UserDto userDto, ConstraintValidatorContext constraintValidatorContext) {
        String password;
        String confirmPassword;
        if(userDto instanceof SignUpClientForm){
            SignUpClientForm form = (SignUpClientForm) userDto;
            password = form.getPassword();
            confirmPassword = form.getConfirmPassword();
        }else{
            if(userDto instanceof SignUpWorkerForm){
                SignUpWorkerForm form = (SignUpWorkerForm) userDto;
                password = form.getPassword();
                confirmPassword = form.getConfirmPassword();
            }else{
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Incorrect json").addConstraintViolation();
                return false;
            }
        }
        return password.equals(confirmPassword);
    }
}
