package com.example.projectexample.validator;

import com.example.projectexample.service.singleton.TransactionSingletonService;
import com.example.projectexample.validator.constraint.TransactionMustExist;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;


@RequiredArgsConstructor
public class TransactionMustExistValidator implements ConstraintValidator<TransactionMustExist, Long> {

    private final TransactionSingletonService transactionSingletonService;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return Objects.nonNull(transactionSingletonService.getTransactionByID(id));
        }catch (NotFoundException e){
            return false;
        }
    }
}
