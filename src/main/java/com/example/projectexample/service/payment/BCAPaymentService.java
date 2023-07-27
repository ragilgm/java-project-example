package com.example.projectexample.service.payment;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("BCA")
@Slf4j
public class BCAPaymentService implements PaymentService{
    @Override
    public void payment(Object obj) {
        log.info("payment using BCA");

    }
}
