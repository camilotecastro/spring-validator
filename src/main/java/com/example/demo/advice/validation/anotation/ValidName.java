package com.example.demo.advice.validation.anotation;


import com.example.demo.advice.validation.validator.ValidNameValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidNameValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface ValidName {
    String message() default "{custom.validation.message}";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
