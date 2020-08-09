package com.ekz.shopeeng.service;

import java.util.List;

import com.ekz.shopeeng.entity.UserCart;

public interface UserCartService {

	List<UserCart> findUserCart(int userId);

	UserCart findExistingProduct(int userId, int productId);
	
	UserCart save(UserCart cart);
	
}
