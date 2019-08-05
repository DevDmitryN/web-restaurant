package com.serviceSystem.service.validation;

import com.serviceSystem.entity.enums.UserRole;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePhoneNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface UniquePhoneNumber {
    String message() default "User with this phone number already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
