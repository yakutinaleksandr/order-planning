package com.example.orderplanning.service.impl;

import com.example.orderplanning.model.Customer;
import com.example.orderplanning.repository.CustomerRepository;
import com.example.orderplanning.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(final Customer customer) {
        return customerRepository.insert(customer);
    }
}
