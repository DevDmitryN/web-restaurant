package com.serviceSystem.service.validation;

import com.serviceSystem.entity.dto.ClientDto;
import com.serviceSystem.entity.dto.UserDto;
import com.serviceSystem.entity.dto.WorkerDto;
import com.serviceSystem.entity.dto.form.SignUpClientForm;
import com.serviceSystem.entity.dto.form.UpdatePasswordForm;
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

    private Validator validator = getJavaxValidator();

    // getting javax.validation validator
    // which uses validation annotations
    // in entities classes
    @Bean
    public Validator getJavaxValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        return validatorFactory.usingContext().getValidator();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        List<Class> supportedClasses = Arrays.asList(
                ClientDto.class,
                WorkerDto.class,
                SignUpClientForm.class,
                UpdatePasswordForm.class);
        return supportedClasses.contains(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Set<ConstraintViolation<Object>> violations = validator.validate(target);

        for (ConstraintViolation<Object> constraintViolation : violations) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }
    }

}
