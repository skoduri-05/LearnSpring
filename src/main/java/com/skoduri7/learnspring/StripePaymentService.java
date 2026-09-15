package com.skoduri7.learnspring;

public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Amount of $" + amount + " has been processed using Stripe.");
    }
}
