package com.crypto.arbitrage.domain.poloniex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradePairPloniexModel {
	
	@JsonProperty("lowestAsk")
	private Double askPrice;
	
	@JsonProperty("highestBid")
	private Double bidPrice;

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
