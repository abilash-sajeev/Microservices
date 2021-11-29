package com.example.outlet.service;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class OutletController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private OutletService outletService;
	
	@RequestMapping("/GetProducts")
	@HystrixCommand(fallbackMethod = "GetFallbackProducts")
	public List<Product> getProducts(){
		return outletService.getProducts();
	}
	public List<Product> GetFallbackProducts(){
		return Arrays.asList(new Product("","noProduct","", ""));
	}
	@RequestMapping("/GetProducts/{id}")
	@HystrixCommand
	public ResponseEntity<Product> getProduct(@PathVariable String id) {
			
			return new ResponseEntity<Product>(outletService.getProduct(id), HttpStatus.OK);
	}
	


}
