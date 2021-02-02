package com.example.orderplanning.repository;

import com.example.orderplanning.model.Warehouse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WarehouseRepository extends MongoRepository<Warehouse, String> {
}
