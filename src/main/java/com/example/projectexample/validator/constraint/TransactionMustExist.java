package com.example.projectexample.validator.constraint;


import com.example.projectexample.validator.TransactionMustExistValidator;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ ElementType.METHOD, ElementType.CONSTRUCTOR,ElementType.TYPE,ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = {TransactionMustExistValidator.class})
@Retention(RUNTIME)
public @interface TransactionMustExist {
}
