package com.example.orderplanning.service.impl;


import com.example.orderplanning.dto.OrderRequestDTO;
import com.example.orderplanning.dto.OrderResponseDTO;
import com.example.orderplanning.exception.CustomerNotFoundException;
import com.example.orderplanning.exception.ProductNotFoundException;
import com.example.orderplanning.model.Customer;
import com.example.orderplanning.model.Location;
import com.example.orderplanning.model.Product;
import com.example.orderplanning.model.Warehouse;
import com.example.orderplanning.repository.CustomerRepository;
import com.example.orderplanning.repository.ProductRepository;
import com.example.orderplanning.repository.WarehouseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private WarehouseRepository warehouseRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void shouldThrowExceptionCustomerNotFound() throws CustomerNotFoundException, ProductNotFoundException {
        // Given
        final String customerId = "customerId";
        given(customerRepository.findById(customerId)).willReturn(Optional.empty());
        final OrderRequestDTO requestDTO = new OrderRequestDTO(customerId, "id");

        // When-Then
        assertThrows(CustomerNotFoundException.class, () -> orderService.createOrder(requestDTO));
    }

    @Test
    public void shouldThrowExceptionProductNotFound() throws CustomerNotFoundException, ProductNotFoundException {
        // Given
        final String customerId = "customerId";
        final String productId = "productId";
        given(customerRepository.findById(customerId)).willReturn(Optional.of(new Customer()));
        final OrderRequestDTO requestDTO = new OrderRequestDTO(customerId, productId);

        // When-Then
        assertThrows(ProductNotFoundException.class, () -> orderService.createOrder(requestDTO));
    }

    @Test
    public void shouldReturnResponse() throws CustomerNotFoundException, ProductNotFoundException {
        // Given
        final String customerId = "customerId";
        final String productId = "productId";

        final OrderRequestDTO request = new OrderRequestDTO(customerId, productId);

        final Customer customer = new Customer();
        customer.setLocation(new Location(0.0,0.0));
        given(customerRepository.findById(customerId)).willReturn(Optional.of(customer));

        final Product product = new Product();
        product.setWarehouseIds(Set.of("1", "2", "3"));
        given(productRepository.findById(anyString())).willReturn(Optional.of(product));


        final Warehouse closest = new Warehouse("1", "w1", new Location(3.0, 4.0));
        final Warehouse w2 = new Warehouse("2", "w2", new Location(10.0, 10.0));
        final Warehouse w3 = new Warehouse("3", "w3", new Location(11.0, 9.0));

        given(warehouseRepository.findAllById(anyIterable())).willReturn(Set.of(closest, w2, w3));

        final OrderResponseDTO expected = new OrderResponseDTO();
        expected.setWarehouseId(closest.getId());
        expected.setWarehouseName(closest.getName());
        expected.setDistance(5.0);

        // When
        final OrderResponseDTO actual = orderService.createOrder(request);

        // Then
        assertEquals(expected, actual);
    }
}
