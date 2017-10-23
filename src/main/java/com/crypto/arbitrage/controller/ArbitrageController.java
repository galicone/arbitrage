package com.crypto.arbitrage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.arbitrage.domain.ArbitrageModel;
import com.crypto.arbitrage.service.ArbitrageService;

@RestController
public class ArbitrageController {

	@Autowired
	private ArbitrageService arbitrageService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/arbitrage")
	public List<ArbitrageModel> calculateArbitrage() {
		return arbitrageService.calculateArbitrage();
	}
}
