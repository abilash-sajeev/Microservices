package com.example.product.sbqs;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityExistsException;

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
		Product product = productRepo.findById(id).get();
		if (productRepo.findById(id).isEmpty())
			throw new NoSuchElementException();
		return product;
				
	}
	public void addProduct(Product topic) {
		if (productRepo.findById(topic.getId()).isEmpty()) {
			productRepo.save(topic);
		}
		else {
			throw new EntityExistsException();
		}
	}
	public void updateProduct(Product topic, String id) {
		if (productRepo.findById(topic.getId()).isEmpty())
			throw new NoSuchElementException();
		productRepo.save(topic);
		
	}
	public void deleteProduct(String id) {
		if (productRepo.findById(id).isEmpty())
			throw new NoSuchElementException();
		productRepo.deleteById(id);
		
	}
}
