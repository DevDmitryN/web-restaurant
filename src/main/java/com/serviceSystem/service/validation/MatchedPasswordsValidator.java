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
        if(userDto instanceof SignUpClientForm){
            SignUpClientForm form = (SignUpClientForm) userDto;
            return form.getPassword().equals(form.getConfirmPassword());
        }
        if(userDto instanceof SignUpWorkerForm){
            SignUpWorkerForm form = (SignUpWorkerForm) userDto;
            return form.getPassword().equals(form.getConfirmPassword());
        }
        return false;
    }
}
