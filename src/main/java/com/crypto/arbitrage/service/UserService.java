package com.crypto.arbitrage.service;

import com.crypto.arbitrage.domain.User;

public interface UserService {
	
	public User findUserByUsername(String username);
	
	public void saveUser(User user);
}

