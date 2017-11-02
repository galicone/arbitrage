package com.crypto.arbitrage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crypto.arbitrage.domain.ArbitrageModel;
import com.crypto.arbitrage.service.ArbitrageService;
import com.crypto.arbitrage.service.BittrexArbitrageService;
import com.crypto.arbitrage.service.CryptopiaArbitrageService;
import com.crypto.arbitrage.service.LivecoinArbitrageService;

@RestController
public class ArbitrageController {

	@Autowired
	private ArbitrageService arbitrageService;
	
	@Autowired
	private LivecoinArbitrageService livecoinArbitrageService;
	
	@Autowired
	private CryptopiaArbitrageService cryptopiaArbitrageService;
	
	@Autowired
	private BittrexArbitrageService bittrexArbitrageService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/arbitrage")
	public List<ArbitrageModel> calculateArbitrage() {
		return arbitrageService.returnCalculationResult();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/arbitrage/livecoin")
	public List<ArbitrageModel> calculateLivecoinArbitrage() {
		return livecoinArbitrageService.calculateArbitrage();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/arbitrage/cryptopia")
	public List<ArbitrageModel> calculateCryptopiaArbitrage() {
		return cryptopiaArbitrageService.calculateArbitrage();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/arbitrage/bittrex")
	public List<ArbitrageModel> calculateBittrexArbitrage() {
		return bittrexArbitrageService.calculateArbitrage();
	}
	
}
