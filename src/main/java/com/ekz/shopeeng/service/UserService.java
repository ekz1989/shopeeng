package com.ekz.shopeeng.service;

import com.ekz.shopeeng.entity.User;

public interface UserService {

	User userLogin(String email, String password);

	User userRegister(User user);
}
