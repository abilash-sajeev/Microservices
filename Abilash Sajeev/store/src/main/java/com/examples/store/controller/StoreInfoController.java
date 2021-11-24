package com.examples.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examples.store.exceptions.ProductNotFoundException;
import com.examples.store.models.ProductData;
import com.examples.store.service.StoreInfoService;

@RestController
public class StoreInfoController {

	@Autowired
	StoreInfoService storeInfoService;
	
	@RequestMapping("/GetProducts")
	public List<ProductData> getProducts(){
		return storeInfoService.getProducts();
	}
	
	@RequestMapping(value =  "/GetProducts/{id}", produces = "application/json")
	public ResponseEntity<ProductData> getProduct(@PathVariable String id) {
		try {
			return new ResponseEntity<>(storeInfoService.getProduct(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
		}
	}
	
}