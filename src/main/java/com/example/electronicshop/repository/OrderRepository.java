package com.example.electronicshop.repository;

import com.example.electronicshop.models.enity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends MongoRepository<Order, String> {
}
