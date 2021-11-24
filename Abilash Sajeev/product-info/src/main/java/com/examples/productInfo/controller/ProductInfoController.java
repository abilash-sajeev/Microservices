package com.examples.productInfo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.examples.productInfo.exceptions.ProductAlreadyExistsException;
import com.examples.productInfo.exceptions.ProductNotFoundException;
import com.examples.productInfo.models.Product;
import com.examples.productInfo.service.ProductInfoService;

@RestController
public class ProductInfoController {
	
	@Autowired
	ProductInfoService productInfoService;
	
	@RequestMapping("/GetProductInfo")
	public List<Product> getProducts(){
		return productInfoService.getProducts();
	}

	@RequestMapping(value = "/GetProductInfo/{id}", produces = "application/json")
//	public ResponseEntity<Optional<Product>> getProduct(@PathVariable String id) {
//		try {
//			return new ResponseEntity<>(productInfoService.getProduct(id), HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
//		}
//	}
	public Optional<Product> getProduct(@PathVariable String id) {
		return productInfoService.getProduct(id);
	}
	
	@RequestMapping(value = "/PostProductInfo", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<Void> addProduct(@RequestBody Product product) {
//		try {
//				productInfoService.addProduct(product);
//				return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity(new ProductAlreadyExistsException().toString(), HttpStatus.CONFLICT);
//		}
//	}
	public void addProduct(@RequestBody Product product) {
		productInfoService.addProduct(product);
	}
	
//	@ExceptionHandler(ProductAlreadyExistsException.class)
//	public ResponseEntity<String> handleProductAlreadyExistsException(){
//		return new ResponseEntity(new ProductAlreadyExistsException().toString(), HttpStatus.CONFLICT);
//	}
	
	
	@RequestMapping(value = "/UpdateProductInfo/{id}", method = RequestMethod.PUT, produces = "application/json")
//	public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
//		try {
//			productInfoService.updateProduct(product);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
//		}
//	}
	public void updateProduct(@RequestBody Product product) {
		productInfoService.updateProduct(product);
	}
	
	@RequestMapping(value = "/DeleteProductInfo/{id}", method = RequestMethod.DELETE, produces = "application/json")
//	public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
//		try {
//			productInfoService.deleteProduct(id);
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
//		}
//	}
	public void deleteProduct(@PathVariable String id) {
		productInfoService.deleteProduct(id);
	}
	
}
