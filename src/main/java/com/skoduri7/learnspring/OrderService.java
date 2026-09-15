package com.skoduri7.learnspring;

public class OrderService {
    public void placeOrder() {
        var paymentService = new StripePaymentService();
        paymentService.processPayment(100.0);
    }
}
