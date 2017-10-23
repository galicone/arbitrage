package com.crypto.arbitrage.domain.cryptopia;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketsCryptopiaModel {

	@JsonProperty("Success")
	private String success;
	
	@JsonProperty("Message")
	private String message;
	
	@JsonProperty("Data")
	private List<TradePairCryptopiaModel> tradePairs = new ArrayList<TradePairCryptopiaModel>();

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

	public List<TradePairCryptopiaModel> getTradePairs() {
		return tradePairs;
	}

	public void setTradePairs(List<TradePairCryptopiaModel> tradePairs) {
		this.tradePairs = tradePairs;
	}
}
