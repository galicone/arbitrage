package com.crypto.arbitrage.service;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Service;

import com.crypto.arbitrage.converter.LivecoinConverter;
import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.domain.livecoin.MarketsLivecoinModel;

@Service
public class LivecoinCalculationService extends AbstractCalculationService {
	
	@Override
	public Map<String, TradePairDomain> getData() {
		Client client = ClientBuilder.newClient(); 
		
		WebTarget target = client.target(getBaseURI());
		
		MarketsLivecoinModel response = target.path(Constants.GET_MARKETS_LIVECOIN).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(MarketsLivecoinModel.class);	
		
		return LivecoinConverter.convertResult(response);
	}
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri(Constants.BASE_URL_LIVECOIN).build();
    }
}
