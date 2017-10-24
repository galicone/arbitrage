package com.crypto.arbitrage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.crypto.arbitrage.domain.ArbitrageModel;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.support.Helper;

public abstract class AbstractArbitrageService {

	public abstract List<ArbitrageModel> calculateArbitrage();
	
	public List<ArbitrageModel> calculateOneWayArbitrage(Map<String, TradePairDomain> tradePairs1, Map<String, TradePairDomain> tradePairs2) {
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
			}
		}
		
		return arbitrages;
	}
	
}
