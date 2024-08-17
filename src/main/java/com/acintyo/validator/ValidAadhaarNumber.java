package com.acintyo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = AadhaarNumberValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAadhaarNumber {
    String message() default "Aadhaar number must be a 12-digit number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
