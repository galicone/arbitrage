package com.crypto.arbitrage.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.crypto.arbitrage.domain.ArbitrageModel;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.support.Helper;

@Service
public class ArbitrageService {

	@Autowired
	private BittrexCalculationService bittrexCalculationService;
	
	@Autowired
	private CryptopiaCalculationService cryptopiaCalculationService;
	
	@Autowired
	private LivecoinCalculationService livecoinCalculationService;
	
	private List<ArbitrageModel> arbitrages =  new ArrayList<ArbitrageModel>();
	
	@Scheduled(initialDelay = 5000, fixedRate = 60000)
	private List<ArbitrageModel> calculateArbitrage() {
		Map<String, TradePairDomain> bittrexTradePairs = bittrexCalculationService.getData();
		Map<String, TradePairDomain> cryptopiaTradePairs = cryptopiaCalculationService.getData();
		Map<String, TradePairDomain> livecoinTradePairs = livecoinCalculationService.getData();
		
		List<ArbitrageModel> arbitragesTmp = new ArrayList<ArbitrageModel>();
		
		List<ArbitrageModel> bittrexCryptopiaArbitrages = calculateArbitrage(bittrexTradePairs, cryptopiaTradePairs);
		List<ArbitrageModel> bittrexLivecoinArbitrages = calculateArbitrage(bittrexTradePairs, livecoinTradePairs);
		List<ArbitrageModel> LivecoinCryptopiaArbitrages = calculateArbitrage(livecoinTradePairs, cryptopiaTradePairs);
		
		
		arbitragesTmp.addAll(bittrexCryptopiaArbitrages);
		arbitragesTmp.addAll(bittrexLivecoinArbitrages);
		arbitragesTmp.addAll(LivecoinCryptopiaArbitrages);
		Collections.sort(arbitragesTmp);
		
		arbitrages.clear();
		arbitrages.addAll(arbitragesTmp);
		
		return arbitrages;
	}
	
	public List<ArbitrageModel> returnCalculationResult(String exchanges) {
		List<String> exchangesList = getListOfExchanges(exchanges);
		
		List<ArbitrageModel> filteredArbitrages = arbitrages.stream()
				.filter(arbitrage -> exchangesList.contains(arbitrage.getSellAt()))
				.filter(arbitrage -> exchangesList.contains(arbitrage.getBuyAt()))
				.collect(Collectors.toList());

		return filteredArbitrages;
	}
	
	private List<ArbitrageModel> calculateArbitrage(Map<String, TradePairDomain> tradePairs1, Map<String, TradePairDomain> tradePairs2) {
		List<ArbitrageModel> arbitrages = new ArrayList<ArbitrageModel>();
		
		for (Map.Entry<String, TradePairDomain> entry : tradePairs1.entrySet()) {
			
			if (tradePairs2.containsKey(entry.getKey())) {
				TradePairDomain tradePair2 = tradePairs2.get(entry.getKey());
				TradePairDomain tradePair1 = entry.getValue();
				
				if (tradePair2.getBidPrice() > tradePair1.getAskPrice()) {
					ArbitrageModel arbitrage = new ArbitrageModel();
					arbitrage.setType(tradePair2.getType());
					arbitrage.setSellAtPrice(tradePair2.getBidPrice());
					arbitrage.setBuyAtPrice(tradePair1.getAskPrice());
					arbitrage.setBuyAt(tradePair1.getExchangeName());
					arbitrage.setSellAt(tradePair2.getExchangeName());
					arbitrage.setDifferencePercentage(Helper.roundValue(((tradePair2.getBidPrice() / tradePair1.getAskPrice()) - 1)* 100));
					
					arbitrages.add(arbitrage);
				}
				
				if (tradePair1.getBidPrice() > tradePair2.getAskPrice()) {
					ArbitrageModel arbitrage = new ArbitrageModel();
					arbitrage.setType(tradePair2.getType());
					arbitrage.setBuyAtPrice(tradePair2.getAskPrice());
					arbitrage.setSellAtPrice(tradePair1.getBidPrice());
					arbitrage.setBuyAt(tradePair2.getExchangeName());
					arbitrage.setSellAt(tradePair1.getExchangeName());
					arbitrage.setDifferencePercentage(Helper.roundValue(((tradePair1.getBidPrice() / tradePair2.getAskPrice()) - 1)* 100));
					
					arbitrages.add(arbitrage);
				}
			}
		}
		
		return arbitrages;
	}
	
	private List<String> getListOfExchanges(String exchanges) {
		if (exchanges != null) {
			return Arrays.asList(exchanges.split(","));
		}
		return new ArrayList<String>();
	}
	
}
