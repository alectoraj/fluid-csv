package com.fluidapi.csv.validaton;

import static com.fluidapi.csv.validaton.FailCheck.failIf;
import static java.util.stream.Collectors.joining;

import java.util.Set;

import com.fluidapi.csv.exception.CsvConstraintViolationException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public interface BeanValidation {

	Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
	
	static <T> void validate(T bean) {
		Set<ConstraintViolation<T>> violations = validator.validate(bean);
		
		failIf( !violations.isEmpty(),
				() -> constructMessage(violations),
				CsvConstraintViolationException::new );
	}

	static <T> String constructMessage(Set<ConstraintViolation<T>> violations) {
		return violations.stream()
				.map(BeanValidation::constructMessage)
				.collect(joining("; "));
	}

	static <T> String constructMessage(ConstraintViolation<T> violation) {
		return "invalid value for '%s' - %s"
				.formatted(violation.getPropertyPath(), violation.getMessage());
	}
	
}
