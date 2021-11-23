package com.examples.productInfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.examples.productInfo.Repository.ProductRepository;
import com.examples.productInfo.exceptions.ProductAlreadyExistsException;
import com.examples.productInfo.exceptions.ProductNotFoundException;
import com.examples.productInfo.models.Product;

@RestController
public class ProductInfoService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping("/GetProductInfo")
	public List<Product> getProducts(){
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	
	@RequestMapping("/GetProductInfo/{id}")
	public ResponseEntity<Optional<Product>> getProduct(@PathVariable String id) {
		try {
			Optional<Product> product = productRepository.findById(id);
			if (product.isEmpty())
				throw new ProductNotFoundException();
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/PostProductInfo", method = RequestMethod.POST)
	public ResponseEntity<Void> addProduct(@RequestBody Product product) {
		try {
			if (productRepository.findById(product.getId()).isEmpty()) {
				productRepository.save(product);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			else {
				throw new ProductNotFoundException();
			}
		} catch (Exception e) {
			return new ResponseEntity(new ProductAlreadyExistsException().toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/UpdateProductInfo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
		try {
			if (productRepository.findById(product.getId()).isEmpty())
				throw new ProductNotFoundException();
			productRepository.save(product);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/DeleteProductInfo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
		try {
			if (productRepository.findById(id).isEmpty())
				throw new ProductNotFoundException();
			productRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new ProductNotFoundException().toString(), HttpStatus.NOT_FOUND);
		}
	}

}
