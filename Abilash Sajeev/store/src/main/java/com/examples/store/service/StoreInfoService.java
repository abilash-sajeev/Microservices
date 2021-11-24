package com.examples.store.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.examples.store.exceptions.ProductNotFoundException;
import com.examples.store.models.Product;
import com.examples.store.models.ProductData;

@Service
public class StoreInfoService {
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<ProductData> getProducts(){
		Product[] productsArray = restTemplate.getForObject("http://localhost:3000/GetProductInfo", Product[].class);
		return Arrays.asList(productsArray).stream()
				.map(product -> new ProductData(product.getId(), product.getProductName(), product.getPrice(), "Unsold"))
				.collect(Collectors.toList());
	}
	
	public ProductData getProduct(String id) {
		Product product = restTemplate.getForObject("http://localhost:3000/GetProductInfo/"+id, Product.class);
		return new ProductData(product.getId(), product.getProductName(), product.getPrice(), "Unsold");
	}

}
