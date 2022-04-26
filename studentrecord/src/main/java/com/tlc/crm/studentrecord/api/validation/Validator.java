package com.tlc.crm.studentrecord.api.validation;

import com.tlc.commons.code.ErrorCode;
import com.tlc.crm.studentrecord.model.Student;
import com.tlc.crm.studentrecord.status.StudentErrorCodes;
import com.tlc.validator.ModelValidator;
import com.tlc.validator.ValidatorAccess;

/**
 * Validator to validate student object.
 *
 * @author ThalaimalaiPandiyanT
 */
public class Validator {

    private static final ModelValidator MODEL_VALIDATOR = ValidatorAccess.get();

    /**
     * Validate the student details.
     *
     * @param student
     * @param groups
     */
    public static void validate(final Student student, final Class... groups) {

        if (!MODEL_VALIDATOR.isValid(student, groups)) {
            throw ErrorCode.get(StudentErrorCodes.VALIDATION_FAILED);
        }
    }
}
