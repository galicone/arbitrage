package com.crypto.arbitrage.domain.cryptopia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradePairCryptopiaModel {

	@JsonProperty("Label")
	private String label;
	
	@JsonProperty("AskPrice")
	private Double askPrice;
	
	@JsonProperty("BidPrice")
	private Double bidPrice;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(Double askPrice) {
		this.askPrice = askPrice;
	}

	public Double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}
	
}
