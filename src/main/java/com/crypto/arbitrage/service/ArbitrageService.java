package com.crypto.arbitrage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crypto.arbitrage.converter.BittrexConverter;
import com.crypto.arbitrage.converter.CryptopiaConverter;
import com.crypto.arbitrage.domain.ArbitrageModel;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.support.Helper;

@Service
public class ArbitrageService {

	@Autowired
	private BittrexCalculationService bittrexCalculationService;
	
	@Autowired
	private CryptopiaCalculationService cryptopiaCalculationService;
	
	public List<ArbitrageModel> calculateArbitrage() {
		Map<String, TradePairDomain> bittrexTradePairs = BittrexConverter.convertResult(bittrexCalculationService.getData());
		Map<String, TradePairDomain> cryptopiaTradePairs = CryptopiaConverter.convertResult(cryptopiaCalculationService.getData());
		List<ArbitrageModel> arbitrages = new ArrayList<ArbitrageModel>();
		
		for (Map.Entry<String, TradePairDomain> entry : bittrexTradePairs.entrySet()) {
			
			if (cryptopiaTradePairs.containsKey(entry.getKey())) {
				TradePairDomain tradePairCryptopia = cryptopiaTradePairs.get(entry.getKey());
				TradePairDomain tradePairBittrex = entry.getValue();
				
				if (tradePairCryptopia.getBidPrice() > tradePairBittrex.getAskPrice()) {
					ArbitrageModel arbitrage = new ArbitrageModel();
					arbitrage.setType(tradePairCryptopia.getType());
					arbitrage.setSellAtPrice(tradePairCryptopia.getBidPrice());
					arbitrage.setBuyAtPrice(tradePairBittrex.getAskPrice());
					arbitrage.setBuyAt("Bittrex");
					arbitrage.setSellAt("Cryptiopia");
					arbitrage.setDifferencePercentage(Helper.roundValue(((tradePairCryptopia.getBidPrice() / tradePairBittrex.getAskPrice()) - 1)* 100));
					
					arbitrages.add(arbitrage);
				}
				
				if (tradePairBittrex.getBidPrice() > tradePairCryptopia.getAskPrice()) {
					ArbitrageModel arbitrage = new ArbitrageModel();
					arbitrage.setType(tradePairCryptopia.getType());
					arbitrage.setBuyAtPrice(tradePairCryptopia.getAskPrice());
					arbitrage.setSellAtPrice(tradePairBittrex.getBidPrice());
					arbitrage.setBuyAt("Cryptiopia");
					arbitrage.setSellAt("Bittrex");
					arbitrage.setDifferencePercentage((Helper.roundValue((tradePairBittrex.getBidPrice() / tradePairCryptopia.getAskPrice()) - 1)* 100));
					
					arbitrages.add(arbitrage);
				}
			}
		}
		
		Collections.sort(arbitrages);
		
		return arbitrages;
	}
}
