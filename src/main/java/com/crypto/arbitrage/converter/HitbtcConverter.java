package com.crypto.arbitrage.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.domain.hitbtc.TradePairHitbtcModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HitbtcConverter {

	public static Map<String, TradePairDomain> convertResult(JsonNode response) {
		Map<String, TradePairDomain> result = new HashMap<>();

		List<TradePairHitbtcModel> tradePairs = new ArrayList<TradePairHitbtcModel>();

		ObjectMapper mapper = new ObjectMapper();

		// Need to convert this way because Jackson has issues with mapping it to List
		try {
			tradePairs = mapper.readValue(mapper.treeAsTokens(response),
					new TypeReference<List<TradePairHitbtcModel>>() {
					});
		} catch (IOException e) {
			return new HashMap<String, TradePairDomain>();
		}

		for (TradePairHitbtcModel tradePair : tradePairs) {
			StringBuilder stringBulder = new StringBuilder();

			// Received type is in format ETHBTC
			String part1 = tradePair.getLabel().substring(0, tradePair.getLabel().length() - 3);
			String part2 = tradePair.getLabel().substring(tradePair.getLabel().length() - 3,
					tradePair.getLabel().length());

			String type = stringBulder.append(part2).append(" - ").append(part1).toString();

			TradePairDomain tradePairDomain = new TradePairDomain();
			tradePairDomain.setAskPrice(tradePair.getAskPrice());
			tradePairDomain.setBidPrice(tradePair.getBidPrice());
			tradePairDomain.setType(type);
			tradePairDomain.setExchangeName(Constants.EXCHANGE_NAME_HITBTC);

			if (tradePairDomain.getAskPrice() != null && tradePairDomain.getBidPrice() != null
					&& tradePairDomain.getAskPrice() != 0 && tradePairDomain.getBidPrice() != 0) {
				result.put(type, tradePairDomain);
			}
		}

		return result;
	}
}
