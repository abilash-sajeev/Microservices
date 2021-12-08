package com.examples.store.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.examples.store.models.Product;
import com.examples.store.models.ProductData;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@RefreshScope
public class StoreInfoService {
	
	Logger logger = LoggerFactory.getLogger(StoreInfoService.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${product.info.url}")
	private String url;
	
	@Value("${admin.console.url}")
	private String adminUrl;
	
	@Value("${help.url}")
	private String helpUrl;
	
	@CircuitBreaker(name = "store-api", fallbackMethod = "getFallbackProducts")
	public List<ProductData> getProducts(){
		Product[] productsArray = restTemplate.getForObject(url, Product[].class);
		return Arrays.asList(productsArray).stream()
				.map(product -> new ProductData(product.getId(), product.getProductName(), product.getPrice(), "Unsold"))
				.collect(Collectors.toList());
	}
	
	@CircuitBreaker(name = "store-api", fallbackMethod = "getFallbackProduct")
	public ProductData getProduct(String id) {
		Product product = restTemplate.getForObject(url+id, Product.class);
		logger.info("Product {} is available", product.getProductName());
		return new ProductData(product.getId(), product.getProductName(), product.getPrice(), "Unsold");
	}

	public ResponseEntity<String> admin() {
		ResponseEntity<String> response = restTemplate.getForEntity(adminUrl, String.class);
		return response;
	}
	
	public ResponseEntity<String> help() {
		ResponseEntity<String> response = restTemplate.getForEntity(helpUrl, String.class);
		return response;
	}
	
	public List<ProductData> getFallbackProducts(Exception ex){
		List<ProductData> products = new ArrayList<>();
		products.add(new ProductData("", "No Product", 0.00, ""));
		return products;
	}
	
	public ProductData getFallbackProduct(String id, Exception ex) {
		return new ProductData("", "No Product", 0.00, "");
	}

}
