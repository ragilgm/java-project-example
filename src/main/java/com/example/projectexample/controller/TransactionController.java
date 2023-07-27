package com.example.projectexample.controller;


import com.example.projectexample.dto.TransactionDTO;
import com.example.projectexample.dto.TransactionRequestDTO;
import com.example.projectexample.service.TransactionService;
import com.example.projectexample.validator.constraint.TransactionMustExist;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody @Valid TransactionRequestDTO requestDTO){
        TransactionDTO transactionDTO = transactionService.createTransaction(requestDTO);
        return new ResponseEntity<>(transactionDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionByID(@PathVariable("id") @Valid @TransactionMustExist Long id) throws NotFoundException {
        TransactionDTO transactionDTO = transactionService.findByID(id);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<TransactionDTO>> getAllTransactions(@Valid TransactionRequestDTO requestDTO, Pageable pageable) {
        Page<TransactionDTO> transactionDTO = transactionService.findAll(requestDTO,pageable);
        return new ResponseEntity<>(transactionDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<TransactionDTO> deleteTransactionByID(@PathVariable("id") @Valid @TransactionMustExist Long id) {
         transactionService.deleteTransactionByID(id);
        return ResponseEntity.noContent().build();
    }


}

