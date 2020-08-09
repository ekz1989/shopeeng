package com.ekz.shopeeng.response;

import com.ekz.shopeeng.entity.Product;

public class ProductItem {
	
	private int productId;
	private String productName;
	private String productImage;
	private String productDesc;
	private int price;

	public ProductItem() {
		
	}

	public ProductItem(Product product) {
		this.setProductId(product.getProductId());
		this.setProductName(product.getProductName());
		this.setProductDesc(product.getProductDesc());
		this.setProductImage(product.getProductImage());
		this.setPrice(product.getPrice());
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
