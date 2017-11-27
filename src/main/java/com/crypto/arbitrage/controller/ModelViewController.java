package com.crypto.arbitrage.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crypto.arbitrage.domain.User;
import com.crypto.arbitrage.exception.ApplicationException;
import com.crypto.arbitrage.service.NotificationService;
import com.crypto.arbitrage.service.RegistrationService;

@Controller
public class ModelViewController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private NotificationService notificationService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/main")
	public String calculateArbitrage(Model model) {
		return "main";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String home(Model model) {
		return "home";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String allOther(Model model) {
		return "home";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/register")
	public String getRegister(User user) {
		return "register";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public String register(@Valid User user, Model model) {
		try {
			registrationService.registerUser(user);
		} catch (ApplicationException e) {
			notificationService.addErrorMessage("Email already registered");
			return "register";
		}
		
		notificationService.addInfoMessage("Successful registration");
		
		return "home";
	}
}
