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

/*@RestController
public class OutletService {
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/GetProducts")
	public List<Product> getProducts(){
		Product[] productsArray = restTemplate.getForObject("http://localhost:8081/GetProductInfo", Product[].class);
		return Arrays.asList(productsArray).stream()
				.map(product -> new Product(product.getId(), product.getProductName(), product.getPrice(), "Unsold"))
				.collect(Collectors.toList());
	}
	
	@RequestMapping("/GetProducts/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable String id) {
		try {
			Product product = restTemplate.getForObject("http://localhost:8081/GetProductInfo/"+id, Product.class);
			return new ResponseEntity<Product>(new Product(product.getId(), product.getProductName(), product.getPrice(), "Unsold"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new AttributeNotFoundException().toString(), HttpStatus.NOT_FOUND);
		}
	}*/



