package com.example.projectexample.service.payment;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("BRI")
@Slf4j
public class BRIPaymentService implements PaymentService{
    @Override
    public void payment(Object obj) {
      log.info("payment using BRI");
    }
}
