package com.crypto.arbitrage.service;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Service;

import com.crypto.arbitrage.converter.CryptopiaConverter;
import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.crypto.arbitrage.domain.cryptopia.MarketsCryptopiaModel;

@Service
public class CryptopiaCommunicationService extends AbstractCommunicationService {
	
	@Override
	public Map<String, TradePairDomain> getData() {
		Client client = ClientBuilder.newClient(); 
		
		WebTarget target = client.target(getBaseURI());
		
		MarketsCryptopiaModel response = target.path(Constants.GET_MARKETS_CRYPTOPIA).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(MarketsCryptopiaModel.class);	
		
		return CryptopiaConverter.convertResult(response);
	}
	
	private static URI getBaseURI() {
        return UriBuilder.fromUri(Constants.BASE_URL_CRYPTOPIA).build();
    }
}
