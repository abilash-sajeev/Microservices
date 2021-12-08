package com.examples.productInfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.examples.productInfo.exceptions.ProductAlreadyExistsException;
import com.examples.productInfo.exceptions.ProductNotFoundException;
import com.examples.productInfo.models.Product;
import com.examples.productInfo.repository.ProductRepository;

@Service
@RefreshScope
public class ProductInfoService {
	
	Logger logger = LoggerFactory.getLogger(ProductInfoService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Value("${help.text}")
	private String helpText;
	
	public List<Product> getProducts(){
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	
	public Optional<Product> getProduct(String id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			logger.error("Product not found", new ProductNotFoundException());
			throw new ProductNotFoundException();
		}
		logger.info("Product {} is available", product.get().getProductName());
		return product;
	}

	public void addProduct(Product product) {
		if (productRepository.findById(product.getId()).isEmpty()) {
			productRepository.save(product);
			logger.info("Added product {}.", product.getProductName());
		}
		else {
			logger.error("Failed to add product {}", product.getProductName(), new ProductAlreadyExistsException());
			throw new ProductAlreadyExistsException();
		}
	}
	
	public void updateProduct(Product product) {
		if (productRepository.findById(product.getId()).isEmpty()) {
			logger.error("Failed to update product {}", product.getProductName(), new ProductNotFoundException());
			throw new ProductNotFoundException();
		}
		productRepository.save(product);
		logger.info("Updated product {}.", product.getProductName());
	}
	
	public void deleteProduct(String id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isEmpty()) {
			logger.error("Failed to delete product.", new ProductNotFoundException());
			throw new ProductNotFoundException();
		}
		productRepository.deleteById(id);
		logger.info("Deleted product {}.", product.get().getProductName());
	}

	public void admin() {
		throw new com.examples.productInfo.exceptions.AccessDeniedException();
	}
	
	public String help() {
		return helpText;
	}

}
