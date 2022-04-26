package com.tlc.crm.studentrecord.api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Custom validation for department name.
 *
 * @author ThalaimalaiPandiyanT
 */
public class DepartmentValidator implements ConstraintValidator<Department, String> {

    /**
     * Validate the department name.
     *
     * @param value
     * @param context
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("((?i)ECE|EEE|MECH|CSE|CIVIL)");
    }
}
