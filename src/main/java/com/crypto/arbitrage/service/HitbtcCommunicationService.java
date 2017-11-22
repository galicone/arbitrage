package com.crypto.arbitrage.service;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Component;

import com.crypto.arbitrage.converter.HitbtcConverter;
import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class HitbtcCommunicationService extends AbstractCommunicationService {
	
	@Override
	public Map<String, TradePairDomain> getData() {
		Client client = ClientBuilder.newClient(); 
		
		WebTarget target = client.target(getBaseURI());
		
		JsonNode response = target.path(Constants.GET_MARKETS_HITBTC).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(JsonNode.class);	
		
		return HitbtcConverter.convertResult(response);
	}
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri(Constants.BASE_URL_HITBTC).build();
    }
}
