package com.examples.productInfo.repository;

import org.springframework.data.repository.CrudRepository;

import com.examples.productInfo.models.Product;

public interface ProductRepository extends CrudRepository<Product, String>{

}
