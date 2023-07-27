package com.example.projectexample.dto;


import com.example.projectexample.constant.PaymentType;
import lombok.Data;

import javax.validation.Valid;
import java.math.BigDecimal;

@Data
@Valid
public class TransactionRequestDTO {
    private PaymentType paymentType;
    private BigDecimal amount;
}
