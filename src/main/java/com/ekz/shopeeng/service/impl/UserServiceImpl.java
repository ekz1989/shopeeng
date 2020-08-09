package com.ekz.shopeeng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekz.shopeeng.entity.User;
import com.ekz.shopeeng.repo.UserRepository;
import com.ekz.shopeeng.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User userLogin(String email, String password) {
		return userRepo.userLogin(email, password);
	}

	@Override
	public User userRegister(User user) {
		return userRepo.save(user);
	}

}
