package com.serviceSystem.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CorrectUpdatedPasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface CorrectUpdatedPassword {
    String message() default "Passwords dont match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default{};
}
