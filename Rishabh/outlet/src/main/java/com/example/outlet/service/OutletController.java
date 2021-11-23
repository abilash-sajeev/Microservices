package com.example.outlet.service;
import java.util.List;
import javax.management.AttributeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OutletController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	private OutletService outletService;
	
	@RequestMapping("/GetProducts")
	public List<Product> getProducts(){
		Product[] productsArray = restTemplate.getForObject("http://localhost:8081/GetProductInfo", Product[].class);
		return outletService.getProducts(productsArray);
	}
	@RequestMapping("/GetProducts/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable String id) {
		try {
			Product product = restTemplate.getForObject("http://localhost:8081/GetProductInfo/"+id, Product.class);
			return new ResponseEntity<Product>(outletService.getProduct(product, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(new AttributeNotFoundException().toString(), HttpStatus.NOT_FOUND);
			//throw new RuntimeException("Product Not Found");
		}
	}


}
