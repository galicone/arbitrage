package com.crypto.arbitrage.domain.livecoin;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketsLivecoinModel {

	@JsonProperty("currencyPairs")
	private List<TradePairLivecoinModel> tradePairs = new ArrayList<TradePairLivecoinModel>();

	public List<TradePairLivecoinModel> getTradePairs() {
		return tradePairs;
	}

	public void setTradePairs(List<TradePairLivecoinModel> tradePairs) {
		this.tradePairs = tradePairs;
	}
}
