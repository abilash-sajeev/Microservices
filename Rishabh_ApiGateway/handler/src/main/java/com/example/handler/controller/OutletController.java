package com.example.handler.controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.handler.product.Product;
import com.example.handler.service.OutletService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
public class OutletController {
	private static final String getProduct ="GetProduct";
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private OutletService outletService;
	
	@RequestMapping("/GetProducts")
	@CircuitBreaker(name = "getproducts", fallbackMethod = "FallbackProducts")
	public List<Product> getProducts(){
		return outletService.getProducts();
	}
	public List<Product> FallbackProducts(Exception e){
		return Arrays.asList(new Product("","noProduct","", ""));
	}
	@RequestMapping("/GetProducts/{id}")
	@CircuitBreaker(name = "")
	public ResponseEntity<Product> getProduct(@PathVariable String id) {
			
			return new ResponseEntity<Product>(outletService.getProduct(id), HttpStatus.OK);
	}
	


}
