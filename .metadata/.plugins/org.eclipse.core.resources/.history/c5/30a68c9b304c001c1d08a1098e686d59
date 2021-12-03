package com.examples.productInfo.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
	@Id
	private String id;
	private String productName;
	private double price;
	
	public Product() {
		super();
	}
	
	public Product(String id, String productName, double price) {
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
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
}
