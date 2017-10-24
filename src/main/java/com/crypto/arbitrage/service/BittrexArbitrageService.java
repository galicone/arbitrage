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
public class BittrexArbitrageService extends AbstractArbitrageService {

	@Autowired
	private BittrexCalculationService bittrexCalculationService;
	
	@Autowired
	private CryptopiaCalculationService cryptopiaCalculationService;
	
	@Autowired
	private LivecoinCalculationService livecoinCalculationService;
	
	@Override
	public  List<ArbitrageModel> calculateArbitrage() {
		Map<String, TradePairDomain> bittrexTradePairs = bittrexCalculationService.getData();
		Map<String, TradePairDomain> cryptopiaTradePairs = cryptopiaCalculationService.getData();
		Map<String, TradePairDomain> livecoinTradePairs = livecoinCalculationService.getData();
		
		List<ArbitrageModel> arbitrages = new ArrayList<ArbitrageModel>();
		
		List<ArbitrageModel> bittrexCryptopiaArbitrages = calculateOneWayArbitrage(bittrexTradePairs, cryptopiaTradePairs);
		List<ArbitrageModel> bittrexLivecoinArbitrages = calculateOneWayArbitrage(bittrexTradePairs, livecoinTradePairs);
		
		arbitrages.addAll(bittrexCryptopiaArbitrages);
		arbitrages.addAll(bittrexLivecoinArbitrages);
		Collections.sort(arbitrages);
		
		return arbitrages;
	}
}
