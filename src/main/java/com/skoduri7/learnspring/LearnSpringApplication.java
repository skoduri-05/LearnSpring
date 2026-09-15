package com.skoduri7.learnspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearnSpringApplication {

    public static void main(String[] args) {
        //SpringApplication.run(LearnSpringApplication.class, args);
        var orderService = new OrderService(new PayPalPaymentService());
        orderService.setPaymentService(new StripePaymentService());
        orderService.placeOrder();
    }

}
