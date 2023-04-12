package com.cts.restapi.custom.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinDataValueVal implements ConstraintValidator<MinDataValue, Integer> {

	int annValue = 0;
	String message = "";

	@Override
	public void initialize(MinDataValue minDataValue) {
		annValue = minDataValue.value();
		if ("".equals(minDataValue.message())) {
			message = "can not be less than " + annValue;
		} else {
			message = minDataValue.message();
		}
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		return value > annValue;
	}
}
