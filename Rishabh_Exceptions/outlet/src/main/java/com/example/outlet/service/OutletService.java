package com.example.outlet.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;


@Service
public class OutletService {
	
	public List<Product> getProducts(Product[] productsArray){
		return Arrays.asList(productsArray).stream()
				.map(product -> new Product(product.getId(), product.getProductName(), product.getPrice(), "Unsold"))
				.collect(Collectors.toList());
	}
	public Product getProduct(Product product, String id) {
		return new Product(product.getId(), product.getProductName(), product.getPrice(), "Unsold");
	}
	
}


