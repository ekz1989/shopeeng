package com.ekz.shopeeng.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductListResponse extends BaseResponse {

	private List<ProductItem> products;

	public List<ProductItem> getProducts() {
		return products;
	}
	public void setProducts(List<ProductItem> products) {
		this.products = products;
	}
}
