package com.crypto.arbitrage.domain.bittrex;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketsBittrexModel {

	@JsonProperty("success")
	private String success;
	
	@JsonProperty("Message")
	private String message;
	
	@JsonProperty("result")
	private List<TradePairBittrexModel> tradePairs = new ArrayList<TradePairBittrexModel>();

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TradePairBittrexModel> getTradePairs() {
		return tradePairs;
	}

	public void setTradePairs(List<TradePairBittrexModel> tradePairs) {
		this.tradePairs = tradePairs;
	}
}
