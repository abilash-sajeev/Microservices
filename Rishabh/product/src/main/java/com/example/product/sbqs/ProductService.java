package com.example.product.sbqs;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	

	public List<Product> getAllProduct(){
		List<Product> products =new ArrayList<>();
		productRepo.findAll().forEach(products::add);
		return products;
	}
	public Product getProduct(String id)
	{
				return productRepo.findById(id).get();
	}
	public void addProduct(Product topic) {
		productRepo.save(topic);
	}
	public void updateProduct(Product topic, String id) {
		productRepo.findById(id).get();
		productRepo.save(topic);
		
	}
	public void deleteProduct(String id) {
		productRepo.deleteById(id);
		
	}
}
