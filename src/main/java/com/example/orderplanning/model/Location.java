package com.example.orderplanning.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class Location {

    @NonNull
    private Double x;

    @NonNull
    private Double y;
}
