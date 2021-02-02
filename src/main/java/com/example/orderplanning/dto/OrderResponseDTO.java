package com.example.orderplanning.dto;

import lombok.Data;

@Data
public class OrderResponseDTO {

    private String warehouseId;

    private String warehouseName;

    private Double distance;
}
