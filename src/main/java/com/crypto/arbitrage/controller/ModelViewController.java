package com.crypto.arbitrage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ModelViewController {
	
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
	public String register(Model model) {
		return "register";
	}
}
