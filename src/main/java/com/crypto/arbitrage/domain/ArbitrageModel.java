package com.crypto.arbitrage.domain;

public class ArbitrageModel {

	private Double buyAtPrice;
	
	private Double sellAtPrice;
	
	private String type;
	
	private String buyAt;
	
	private String sellAt;
	
	private Double differencePercentage;

	public Double getSellAtPrice() {
		return sellAtPrice;
	}

	public void setSellAtPrice(Double sellAtPrice) {
		this.sellAtPrice = sellAtPrice;
	}

	public Double getBuyAtPrice() {
		return buyAtPrice;
	}

	public void setBuyAtPrice(Double buyAtPrice) {
		this.buyAtPrice = buyAtPrice;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBuyAt() {
		return buyAt;
	}

	public void setBuyAt(String buyAt) {
		this.buyAt = buyAt;
	}

	public String getSellAt() {
		return sellAt;
	}

	public void setSellAt(String sellAt) {
		this.sellAt = sellAt;
	}
	
	public Double getDifferencePercentage() {
		return differencePercentage;
	}

	public void setDifferencePercentage(Double differencePercentage) {
		this.differencePercentage = differencePercentage;
	}
}
