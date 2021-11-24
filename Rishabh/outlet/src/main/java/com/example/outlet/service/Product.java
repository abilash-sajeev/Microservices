package com.example.outlet.service;


public class Product {

	
	private String id;
	private String productName;
	private String price;
	private String status;
	
	



	public Product() {
		
	}
	
	
	public Product(String id, String productName, String price, String status) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.status =status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
