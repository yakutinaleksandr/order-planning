package com.example.orderplanning.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "warehouses")
public class Warehouse {

    @Id
    private String id;

    private String name;

    @NonNull
    private Location location;
}
