package com.example.orderplanning.service.impl;


import com.example.orderplanning.model.Customer;
import com.example.orderplanning.model.Location;
import com.example.orderplanning.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository repository;

    @InjectMocks
    private CustomerServiceImpl service;

    @Test
    public void createCustomer() {
        // Given
        final Customer customer = new Customer(null, "customer", new Location(1.0,1.0));

        // When
        service.createCustomer(customer);

        // Then
        verify(repository, times(1)).insert(customer);
    }
}