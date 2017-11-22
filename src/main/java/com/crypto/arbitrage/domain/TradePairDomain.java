package com.crypto.arbitrage.domain;

public class TradePairDomain {
	
	private Double askPrice;
	
	private Double bidPrice;
	
	private String type;
	
	private String exchangeName;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradePairDomain [askPrice=");
		builder.append(askPrice);
		builder.append(", bidPrice=");
		builder.append(bidPrice);
		builder.append(", type=");
		builder.append(type);
		builder.append(", exchangeName=");
		builder.append(exchangeName);
		builder.append("]");
		return builder.toString();
	}
}
