package com.example.product.sbqs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public Product getProduct(@PathVariable String id)
	{
		return productService.getProduct(id);
	}
	@RequestMapping(method=RequestMethod.POST, value="/PostProductInfo")
	public void addProduct(@RequestBody Product product)
	{
		productService.addProduct(product);
	}
	@RequestMapping(method=RequestMethod.PUT, value="/UpdateProductInfo/{id}")
	public void updateProduct(@RequestBody Product product, @PathVariable String id)
	{
		productService.updateProduct(product,id);
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/DeleteProductInfo/{id}")
	public void deleteProduct(@PathVariable String id)
	{
		productService.deleteProduct(id);
	}

}
