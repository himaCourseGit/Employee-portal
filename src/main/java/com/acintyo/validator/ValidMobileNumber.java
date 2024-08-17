package com.acintyo.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = MobileNumberValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMobileNumber {
	String message() default "Mobile number must be a 10-digit number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
