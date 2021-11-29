package com.example.product.sbqs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/GetProductInfo")
	public List<Product> getAllProduct() {
		return productService.getAllProduct();
	}
	@RequestMapping("/GetProductInfo/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable String id) {
		return new ResponseEntity<Product>(productService.getProduct(id), HttpStatus.OK);
	}
	@RequestMapping(method=RequestMethod.POST, value="/PostProductInfo")
	public ResponseEntity<Void> addProduct(@RequestBody Product product)
	{
		productService.addProduct(product);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	@RequestMapping(method=RequestMethod.PUT, value="/UpdateProductInfo/{id}")
	public ResponseEntity<Void> updateProduct(@RequestBody Product product, @PathVariable String id)
	{
		productService.updateProduct(product,id); 
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/DeleteProductInfo/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable String id)
	{
		productService.deleteProduct(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
