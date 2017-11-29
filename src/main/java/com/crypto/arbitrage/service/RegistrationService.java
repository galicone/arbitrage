package com.crypto.arbitrage.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.arbitrage.domain.User;
import com.crypto.arbitrage.exception.ApplicationException;

@Service
public class RegistrationService {
	
	@Autowired
	private UserService userService;

	
	public User registerUser(User user) throws ApplicationException {
		if (userService.findUserByUsername(user.getUsername()) != null) {
			throw new ApplicationException("Customer already exist");
		}
		
		user.setEnabled(true);
		user.setDateCreated(new Timestamp(System.currentTimeMillis()));
		userService.saveUser(user);
		
		return user;
	}
}
