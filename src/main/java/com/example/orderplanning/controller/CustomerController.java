package com.example.orderplanning.controller;

import com.example.orderplanning.model.Customer;
import com.example.orderplanning.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Method for adding new customer.
     *
     * @param customer  dto with new distance to be added
     * @return created customer
     */
    @PostMapping
    public Customer createCustomer(@RequestBody final Customer customer) {
        return customerService.createCustomer(customer);
    }
}
