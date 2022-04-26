package com.tlc.crm.studentrecord.status;

import com.tlc.commons.code.ErrorCodeGroup;
import com.tlc.commons.code.ErrorCodeProvider;

/**
 * Provides the custom error codes.
 *
 * @author ThalaimalaiPandiyanT
 */
public enum StudentErrorCodes implements ErrorCodeProvider {

    VALIDATION_FAILED(0x01),
    INVALID_ID(0x02)
    ;

    private final int code;

    StudentErrorCodes(int localCode) {
        this.code = StudentErrorCodes.StudentErrorCodeGroup.GROUP.getConvertedCode(localCode);
    }

    @Override
    public int getCode() {
        return code;
    }

    /**
     * Class to get the instance of student error codes.
     */
    private static class StudentErrorCodeGroup implements ErrorCodeGroup {

        private static final ErrorCodeGroup GROUP = new StudentErrorCodes.StudentErrorCodeGroup();

        @Override
        public int getPrefix() {
            return 0x10_0_0000;
        }
    }
}
