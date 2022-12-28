package com.iontech.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.iontech.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{
    
}
