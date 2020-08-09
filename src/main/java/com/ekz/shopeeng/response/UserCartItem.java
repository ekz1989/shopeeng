package com.ekz.shopeeng.response;

import com.ekz.shopeeng.entity.Product;
import com.ekz.shopeeng.entity.UserCart;

public class UserCartItem {
	private int cartId;
	private int quantity;
	private Product product;

	public UserCartItem() {

	}
	
	public UserCartItem(UserCart cart) {
		this.setCartId(cart.getCartId());
		this.setQuantity(cart.getQuantity());
		this.setProduct(cart.getProduct());
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
