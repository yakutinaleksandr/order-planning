package com.example.orderplanning.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;

    /*
    * Assumption: number of products is not important,
    * so if specific is in list of ids, then product will be at this warehouse
    * */
    private Set<String> warehouseIds;
}
