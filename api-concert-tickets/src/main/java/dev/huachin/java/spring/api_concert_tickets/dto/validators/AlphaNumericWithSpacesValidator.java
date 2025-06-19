package dev.huachin.java.spring.api_concert_tickets.dto.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AlphaNumericWithSpacesValidator implements ConstraintValidator<AlphaNumericWithSpaces, String> {
    private static final String ALPHA_NUMERIC_WITH_SPACES_REGEX = "^[a-zA-Z0-9 ]+$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isBlank()) {
            return true; // Null values are considered valid
        }

        return value.matches(ALPHA_NUMERIC_WITH_SPACES_REGEX);
    }
}
