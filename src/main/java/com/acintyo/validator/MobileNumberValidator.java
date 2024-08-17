package com.acintyo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MobileNumberValidator implements ConstraintValidator<ValidMobileNumber, Long> {

    @Override
    public void initialize(ValidMobileNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long aadhaarNumber, ConstraintValidatorContext context) {
        if (aadhaarNumber == null) {
            return false;
        }
        String aadhaarNumberStr = aadhaarNumber.toString();
        return aadhaarNumberStr.length() == 10 && aadhaarNumberStr.matches("\\d{10}");       
    }
	

}
