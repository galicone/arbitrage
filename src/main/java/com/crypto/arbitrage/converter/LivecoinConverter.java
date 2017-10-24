package com.crypto.arbitrage.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.domain.livecoin.MarketsLivecoinModel;
import com.crypto.arbitrage.domain.livecoin.TradePairLivecoinModel;

public class LivecoinConverter {

	public static Map<String, TradePairDomain> convertResult(MarketsLivecoinModel marketsLivecoinModel) {
		Map<String, TradePairDomain> result = new HashMap<>();
		
		List<TradePairLivecoinModel> tradePairs = marketsLivecoinModel.getTradePairs();
		
		for (TradePairLivecoinModel tradePair : tradePairs) {
			// Done to be in a format BTCXYZ
			String[] parts = tradePair.getLabel().split("\\/");
			String type = parts[1] + parts[0];
			
			TradePairDomain tradePairDomain = new TradePairDomain();
			tradePairDomain.setAskPrice(tradePair.getAskPrice());
			tradePairDomain.setBidPrice(tradePair.getBidPrice());
			tradePairDomain.setType(type);
			tradePairDomain.setExchangeName(Constants.EXCHANGE_NAME_LIVECOIN);
			
			if (tradePairDomain.getAskPrice() != null && tradePairDomain.getBidPrice() != null 
					&& tradePairDomain.getAskPrice() != 0 && tradePairDomain.getBidPrice() != 0) {
				result.put(type, tradePairDomain);
			}
		}
		
		return result;
	}

}
