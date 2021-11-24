package com.examples.store.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
