package com.crypto.arbitrage.domain.livecoin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradePairLivecoinModel {

	@JsonProperty("symbol")
	private String label;
	
	@JsonProperty("minAsk")
	private Double askPrice;
	
	@JsonProperty("maxBid")
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
