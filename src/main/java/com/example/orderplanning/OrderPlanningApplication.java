package com.example.orderplanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class OrderPlanningApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderPlanningApplication.class, args);
    }

}
