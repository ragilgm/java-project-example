package com.example.projectexample.service;


import com.example.projectexample.constant.BaseConstant;
import com.example.projectexample.dto.TransactionDTO;
import com.example.projectexample.dto.TransactionRequestDTO;
import com.example.projectexample.entity.TransactionEntity;
import com.example.projectexample.entity.TransactionEntity_;
import com.example.projectexample.helper.GenericSpecificationHelper;
import com.example.projectexample.helper.model.SearchCriteria;
import com.example.projectexample.repository.TransactionRepository;
import com.example.projectexample.service.payment.PaymentService;
import com.example.projectexample.service.singleton.TransactionSingletonService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {


    private final TransactionRepository transactionRepository;
    private final TransactionSingletonService transactionSingletonService;
    private final ApplicationContext applicationContext;

    private final MapperFacade mapperFacade;


    // example create transaction
    public TransactionDTO createTransaction(TransactionRequestDTO requestDTO) {

        // example abstraction
        PaymentService paymentService = applicationContext.getBean(requestDTO.getPaymentType().name(),PaymentService.class);
        paymentService.payment(requestDTO);


        TransactionEntity transactionEntity = mapperFacade.map(requestDTO,TransactionEntity.class);
        LocalDateTime localDateTime = LocalDateTime.now();
        transactionEntity.setCreatedAt(localDateTime)
                .setCreatedBy(BaseConstant.SYSTEM)
                .setUpdatedAt(localDateTime)
                .setUpdatedBy(BaseConstant.SYSTEM);
       transactionEntity = transactionRepository.save(transactionEntity);
       return mapperFacade.map(transactionEntity,TransactionDTO.class);
    }



    public TransactionDTO findByID(Long id) throws NotFoundException {
        return mapperFacade.map(transactionSingletonService.getTransactionByID(id),TransactionDTO.class);
    }


    // example pagination
    public Page<TransactionDTO> findAll(TransactionRequestDTO requestDTO, Pageable pageable){
        GenericSpecificationHelper<TransactionEntity> genericSpecificationService = new GenericSpecificationHelper<>();
        genericSpecificationService.add(new SearchCriteria<>(TransactionEntity_.paymentType.getName(), requestDTO.getPaymentType(), SearchCriteria.SearchOperation.EQUAL));
        genericSpecificationService.add(new SearchCriteria<>(TransactionEntity_.amount.getName(), requestDTO.getAmount(), SearchCriteria.SearchOperation.EQUAL));
        return transactionRepository.findAll(genericSpecificationService,pageable)
                .map(trx-> mapperFacade.map(trx,TransactionDTO.class));
    }

    public void deleteTransactionByID(Long id) {
        transactionRepository.deleteById(id);
    }
}
