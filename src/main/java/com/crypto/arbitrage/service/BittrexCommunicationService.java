package com.crypto.arbitrage.service;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Service;

import com.crypto.arbitrage.converter.BittrexConverter;
import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.domain.bittrex.MarketsBittrexModel;

@Service
public class BittrexCommunicationService extends AbstractCommunicationService {

	@Override
	public Map<String, TradePairDomain> getData() {
		Client client = ClientBuilder.newClient(); 
		
		WebTarget target = client.target(getBaseURI());
		
		MarketsBittrexModel response = target.path(Constants.GET_MARKETS_BITTREX).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(MarketsBittrexModel.class);
		
		return BittrexConverter.convertResult(response);
	}
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri(Constants.BASE_URL_BITTREX).build();
    }
}
