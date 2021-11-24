package com.example.product.sbqs;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	private String id;
	private String productName;
	private String price;
	
	
	public Product() {
		
	}
	
	
	public Product(String id, String productName, String price) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

}
