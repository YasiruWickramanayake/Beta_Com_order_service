package com.betacom.infra;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.betacom.*"})
public class OrderApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplicationStart.class);

    }
}