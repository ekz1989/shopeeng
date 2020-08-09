package com.ekz.shopeeng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekz.shopeeng.entity.UserCart;
import com.ekz.shopeeng.repo.UserCartRepository;
import com.ekz.shopeeng.service.UserCartService;

@Service
public class UserCartServiceImpl implements UserCartService {

	@Autowired
	private UserCartRepository cartRepo;

	@Override
	public List<UserCart> findUserCart(int userId) {
		return (List<UserCart>) cartRepo.findUserCart(userId);
	}

	@Override
	public UserCart findExistingProduct(int userId, int productId) {
		return cartRepo.findExistingProduct(userId, productId);
	}

	@Override
	public UserCart save(UserCart cart) {
		return cartRepo.save(cart);
	}
	

}
