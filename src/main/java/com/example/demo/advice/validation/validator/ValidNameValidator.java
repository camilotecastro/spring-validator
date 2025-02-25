package com.example.demo.advice.validation.validator;

import com.example.demo.advice.validation.anotation.ValidName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;
import static org.apache.tomcat.util.http.parser.HttpParser.isAlpha;

public class ValidNameValidator implements ConstraintValidator<ValidName, String> {

    public static final int MAXIMA_LONGITUD_VALUE = 15;
    private static final int MINIMA_LONGITUD_VALUE = 3;

    @Override
    public void initialize(ValidName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (isNull(value))
            return false;

        if (value.isBlank())
            return false;

        if (value.length() > MAXIMA_LONGITUD_VALUE)
            return false;

        if (value.length() < MINIMA_LONGITUD_VALUE)
            return false;

        if (!Character.isUpperCase(value.charAt(0)))
            return false;


        return true;
    }

}
