package com.example.projectexample.service.singleton;


import com.example.projectexample.entity.TransactionEntity;
import com.example.projectexample.repository.TransactionRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

@Service
@RequestScope
@RequiredArgsConstructor
public class TransactionSingletonService {

    private TransactionEntity transactionEntity;

    private final TransactionRepository transactionRepository;

    public TransactionEntity getTransactionByID(Long id) throws NotFoundException {
        if (Objects.isNull(transactionEntity)){
           transactionEntity= transactionRepository.findById(id)
                    .orElseThrow(()-> new NotFoundException("Transaction not found"));
        }
        return transactionEntity;
    }



}
