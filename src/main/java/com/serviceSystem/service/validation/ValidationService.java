package com.serviceSystem.service.validation;

import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.entity.dto.OrderDto;
import com.serviceSystem.entity.dto.UserDto;
import com.serviceSystem.entity.dto.WorkerDto;
import com.serviceSystem.entity.dto.form.SignUpClientForm;
import com.serviceSystem.entity.dto.form.UpdatePasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class ValidationService implements org.springframework.validation.Validator{

//    private Validator validator = getJavaxValidator();

    // getting javax.validation validator
    // which uses validation annotations
    // in entities classes


    @Autowired
    private Validator javaxValidator;
    @Override
    public boolean supports(Class<?> clazz) {
        List<Class> supportedClasses = Arrays.asList(
                ClientDto.class,
                WorkerDto.class,
                SignUpClientForm.class,
                UpdatePasswordForm.class,
                OrderDto.class);
        return supportedClasses.contains(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Set<ConstraintViolation<Object>> violations = javaxValidator.validate(target);

        for (ConstraintViolation<Object> constraintViolation : violations) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }
    }

}
