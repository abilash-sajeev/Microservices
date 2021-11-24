package com.examples.productInfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.productInfo.exceptions.ProductAlreadyExistsException;
import com.examples.productInfo.exceptions.ProductNotFoundException;
import com.examples.productInfo.models.Product;
import com.examples.productInfo.repository.ProductRepository;

@Service
public class ProductInfoService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getProducts(){
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	
	public Optional<Product> getProduct(String id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty())
			throw new ProductNotFoundException();
		return product;
	}

	public void addProduct(Product product) {
		if (productRepository.findById(product.getId()).isEmpty()) {
			productRepository.save(product);
		}
		else {
			throw new ProductAlreadyExistsException();
		}
	}
	
	public void updateProduct(Product product) {
		if (productRepository.findById(product.getId()).isEmpty())
			throw new ProductNotFoundException();
		productRepository.save(product);
	}
	
	public void deleteProduct(String id) {
		if (productRepository.findById(id).isEmpty())
			throw new ProductNotFoundException();
		productRepository.deleteById(id);
	}

}
