package com.example.orderplanning.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class OrderRequestDTO {
    @NonNull
    private String customerId;

    @NonNull
    private String productId;
}
