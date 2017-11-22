package com.crypto.arbitrage.domain.hitbtc;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketsHitbtcModel {

	@JsonProperty("result")
	private List<TradePairHitbtcModel> tradePairs = new ArrayList<TradePairHitbtcModel>();

	public List<TradePairHitbtcModel> getTradePairs() {
		return tradePairs;
	}

	public void setTradePairs(List<TradePairHitbtcModel> tradePairs) {
		this.tradePairs = tradePairs;
	}
}
