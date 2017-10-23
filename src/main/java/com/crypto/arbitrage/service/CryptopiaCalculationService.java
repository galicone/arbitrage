package com.crypto.arbitrage.service;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Service;

import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.cryptopia.MarketsCryptopiaModel;

@Service
public class CryptopiaCalculationService {
	
	public MarketsCryptopiaModel getData() {
		Client client = ClientBuilder.newClient(); 
		
		WebTarget target = client.target(getBaseURI());
		
		MarketsCryptopiaModel response = target.path(Constants.CRYPTOPIA_GET_MARKETS).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(MarketsCryptopiaModel.class);	
		
		return response;
	}
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri(Constants.CRYPTOPIA_BASE_URL).build();
    }
}
