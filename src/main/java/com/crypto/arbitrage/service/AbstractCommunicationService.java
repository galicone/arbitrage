package com.crypto.arbitrage.service;

import java.util.Map;

import com.crypto.arbitrage.domain.TradePairDomain;

public abstract class AbstractCommunicationService {
	
	public abstract Map<String, TradePairDomain> getData();
}
