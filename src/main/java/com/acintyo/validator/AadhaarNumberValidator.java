package com.acintyo.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AadhaarNumberValidator implements ConstraintValidator<ValidAadhaarNumber, Long> {

    @Override
    public void initialize(ValidAadhaarNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(Long aadhaarNumber, ConstraintValidatorContext context) {
        if (aadhaarNumber == null) {
            return false;
        }
        String aadhaarNumberStr = aadhaarNumber.toString();
        return aadhaarNumberStr.length() == 12 && aadhaarNumberStr.matches("\\d{12}");       
    }
    
    
}
