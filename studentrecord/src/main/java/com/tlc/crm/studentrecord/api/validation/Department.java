package com.tlc.crm.studentrecord.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DepartmentValidator.class)
@Documented
public @interface Department {

    String message() default "Invalid Department Name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
