package com.skoduri7.learnspring;

import org.springframework.stereotype.Service;

@Service
public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Amount of $" + amount + " has been processed using PayPal.");
    }
}
