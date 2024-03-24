package com.betacom.packaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.betacom"})
@EntityScan(basePackages = {"com.betacom"})
@EnableJpaRepositories(basePackages = {"com.betacom"})
public class OrderCommandServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(OrderCommandServiceRunner.class);
    }
}