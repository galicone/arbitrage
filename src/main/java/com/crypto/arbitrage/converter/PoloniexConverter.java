package com.crypto.arbitrage.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.domain.poloniex.TradePairPloniexModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PoloniexConverter {

	public static Map<String, TradePairDomain> convertResult(JsonNode response) {
		Map<String, TradePairPloniexModel> data = new HashMap<String, TradePairPloniexModel>();

		ObjectMapper mapper = new ObjectMapper();

		Map<String, TradePairDomain> result = new HashMap<>();
		
		// Need to convert this way because Jackson has issues with mapping it to Map
		try {
			data = mapper.readValue(mapper.treeAsTokens(response),
					new TypeReference<Map<String, TradePairPloniexModel>>() {
					});
		} catch (IOException e) {
			return new HashMap<String, TradePairDomain>();
		}

		for (Map.Entry<String, TradePairPloniexModel> entry : data.entrySet()) {
			String type = entry.getKey().replace("_", " - ");

			TradePairDomain tradePairDomain = new TradePairDomain();
			tradePairDomain.setAskPrice(entry.getValue().getAskPrice());
			tradePairDomain.setBidPrice(entry.getValue().getBidPrice());
			tradePairDomain.setType(type);
			tradePairDomain.setExchangeName(Constants.EXCHANGE_NAME_POLONIEX);

			if (tradePairDomain.getAskPrice() != null && tradePairDomain.getBidPrice() != null
					&& tradePairDomain.getAskPrice() != 0 && tradePairDomain.getBidPrice() != 0) {
				result.put(type, tradePairDomain);
			}
		}

		return result;
	}

}
