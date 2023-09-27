package com.microservice.productapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservice.productapp.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
	
	

}
