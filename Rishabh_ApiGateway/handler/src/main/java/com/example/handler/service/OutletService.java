package com.example.handler.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.handler.product.Product;


@Service
public class OutletService  {
	@Autowired
	RestTemplate restTemplate;
	
	public List<Product> getProducts(){
		Product[] productsArray = restTemplate.getForObject("http://localhost:8081/GetProductInfo", Product[].class);
		return Arrays.asList(productsArray).stream()
				.map(product -> new Product(product.getId(), product.getProductName(), product.getPrice(), "Unsold"))
				.collect(Collectors.toList());
	}
	public Product getProduct(String id) {
		Product product = restTemplate.getForObject("http://localhost:8081/GetProductInfo/"+id, Product.class);
		return new Product(product.getId(), product.getProductName(), product.getPrice(), "Unsold");
	}
	
}


