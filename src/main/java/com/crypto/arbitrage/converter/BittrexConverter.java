package com.crypto.arbitrage.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.domain.bittrex.MarketsBittrexModel;
import com.crypto.arbitrage.domain.bittrex.TradePairBittrexModel;

public class BittrexConverter {

	public static Map<String, TradePairDomain> convertResult(MarketsBittrexModel marketsBittrexModel) {
		Map<String, TradePairDomain> result = new HashMap<>();
		
		List<TradePairBittrexModel> tradePairs = marketsBittrexModel.getTradePairs();
		
		for (TradePairBittrexModel tradePair : tradePairs) {
			String type = tradePair.getLabel().replace("-", " - ");
			
			TradePairDomain tradePairDomain = new TradePairDomain();
			tradePairDomain.setAskPrice(tradePair.getAskPrice());
			tradePairDomain.setBidPrice(tradePair.getBidPrice());
			tradePairDomain.setType(type);
			tradePairDomain.setExchangeName(Constants.EXCHANGE_NAME_BITTREX);
			
			if (tradePairDomain.getAskPrice() != null && tradePairDomain.getBidPrice() != null 
					&& tradePairDomain.getAskPrice() != 0 && tradePairDomain.getBidPrice() != 0) {
				result.put(type, tradePairDomain);
			}
		}
		
		return result;
	}
	
}
