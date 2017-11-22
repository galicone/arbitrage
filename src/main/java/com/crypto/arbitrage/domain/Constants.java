package com.crypto.arbitrage.domain;

import java.util.ArrayList;
import java.util.List;

public final class Constants {

	public static String BASE_URL_CRYPTOPIA = "https://www.cryptopia.co.nz/api/";
	public static String BASE_URL_BITTREX = "https://bittrex.com/api/v1.1/public/";
	public static String BASE_URL_LIVECOIN = "https://api.livecoin.net/";
	public static String BASE_URL_POLONIEX = "https://poloniex.com/public";
	public static String BASE_URL_HITBTC = "https://api.hitbtc.com/api/2/public/";
	
	public static String GET_MARKETS_CRYPTOPIA = "GetMarkets";
	public static String GET_MARKETS_BITTREX = "getmarketsummaries";
	public static String GET_MARKETS_LIVECOIN = "exchange/maxbid_minask";
	public static String GET_MARKETS_HITBTC = "ticker";
	
	public static String GET_MARKETS_POLONIEX_QUERY_PARAM_1 = "command";
	public static String GET_MARKETS_POLONIEX_QUERY_PARAM_2 = "returnTicker";
	
	public static String EXCHANGE_NAME_CRYPTOPIA = "Cryptopia";
	public static String EXCHANGE_NAME_BITTREX = "Bittrex";
	public static String EXCHANGE_NAME_LIVECOIN = "Livecoin";
	public static String EXCHANGE_NAME_POLONIEX = "Poloniex";
	public static String EXCHANGE_NAME_HITBTC = "Hitbtc";
	
	public static List<String> getCurrenciesForDelisting() {
		List<String> delistingList = new ArrayList<String>();
		
		delistingList.add("BTC - BCC");
		delistingList.add("ETH - BCC");
		delistingList.add("BTC - BTG");
		delistingList.add("BTC - BTM");
		
		return delistingList;
	}
}
