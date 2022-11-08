package com.example.electronicshop.repository;


import com.example.electronicshop.models.enity.OrderProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends MongoRepository<OrderProduct, String> {
}
