package com.crypto.arbitrage.service;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.springframework.stereotype.Component;

import com.crypto.arbitrage.converter.PoloniexConverter;
import com.crypto.arbitrage.domain.Constants;
import com.crypto.arbitrage.domain.TradePairDomain;
import com.fasterxml.jackson.databind.JsonNode;

@Component
public class PoloniexCommunicationService extends AbstractCommunicationService {

	@Override
	public Map<String, TradePairDomain> getData() {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(getBaseURI());

		JsonNode response = target
				.queryParam(Constants.GET_MARKETS_POLONIEX_QUERY_PARAM_1, Constants.GET_MARKETS_POLONIEX_QUERY_PARAM_2)
				.request().accept(MediaType.APPLICATION_JSON).get(JsonNode.class);

		return PoloniexConverter.convertResult(response);
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri(Constants.BASE_URL_POLONIEX).build();
	}

}
