package com.example.electronicshop.repository;


import com.example.electronicshop.models.enity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmailAndState(String email, String state);
    Optional<User> findUserByIdAndState(String id, String state);
    Optional<User> findUserByNameAndState(String name, String state);
    boolean existsByEmail(String email);
}
