package com.examples.store.models;

public class ProductData extends Product {
	
	private String productStatus;
	
	public ProductData() {
		super();
	}

	public ProductData(String id, String productName, double price, String status) {
		super(id, productName, price);
		this.productStatus = status;
	}

	public String getStatus() {
		return productStatus;
	}

	public void setStatus(String status) {
		this.productStatus = status;
	}

}
