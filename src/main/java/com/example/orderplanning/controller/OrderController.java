package com.example.orderplanning.controller;

import com.example.orderplanning.dto.OrderRequestDTO;
import com.example.orderplanning.dto.OrderResponseDTO;
import com.example.orderplanning.exception.CustomerNotFoundException;
import com.example.orderplanning.exception.ProductNotFoundException;
import com.example.orderplanning.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * Method for placing an order for customer.
     *
     * @param dto  dto with customer's id and product's id
     * @return warehouse and distance to customer
     */
    @PostMapping
    public OrderResponseDTO placeOrder(@RequestBody final OrderRequestDTO dto)
            throws CustomerNotFoundException, ProductNotFoundException {
        return orderService.createOrder(dto);
    }
}
