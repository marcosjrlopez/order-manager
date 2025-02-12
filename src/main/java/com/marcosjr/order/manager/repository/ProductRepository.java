package com.marcosjr.order.manager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.marcosjr.order.manager.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
