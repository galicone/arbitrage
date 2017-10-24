package com.crypto.arbitrage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.arbitrage.domain.ArbitrageModel;
import com.crypto.arbitrage.domain.TradePairDomain;

@Service
public class LivecoinArbitrageService extends AbstractArbitrageService {

	@Autowired
	private BittrexCalculationService bittrexCalculationService;
	
	@Autowired
	private CryptopiaCalculationService cryptopiaCalculationService;
	
	@Autowired
	private LivecoinCalculationService livecoinCalculationService;
	
	@Override
	public List<ArbitrageModel> calculateArbitrage() {
		Map<String, TradePairDomain> livecoinTradePairs = livecoinCalculationService.getData();
		Map<String, TradePairDomain> bittrexTradePairs = bittrexCalculationService.getData();
		Map<String, TradePairDomain> cryptopiaTradePairs = cryptopiaCalculationService.getData();
		
		List<ArbitrageModel> arbitrages = new ArrayList<ArbitrageModel>();
		
		List<ArbitrageModel> livecoinCryptopiaArbitrages = calculateOneWayArbitrage(livecoinTradePairs, cryptopiaTradePairs);
		List<ArbitrageModel> livecoinBittrexArbitrages = calculateOneWayArbitrage(livecoinTradePairs, bittrexTradePairs);
		
		arbitrages.addAll(livecoinCryptopiaArbitrages);
		arbitrages.addAll(livecoinBittrexArbitrages);
		Collections.sort(arbitrages);
		
		return arbitrages;
	}
}
