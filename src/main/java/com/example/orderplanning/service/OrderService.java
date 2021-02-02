package com.example.orderplanning.service;

import com.example.orderplanning.dto.OrderRequestDTO;
import com.example.orderplanning.dto.OrderResponseDTO;
import com.example.orderplanning.exception.CustomerNotFoundException;
import com.example.orderplanning.exception.ProductNotFoundException;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO dto) throws CustomerNotFoundException, ProductNotFoundException;
}
