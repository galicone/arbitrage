package com.crypto.arbitrage.support;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Helper {

	public static double roundValue(Double value) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(3, RoundingMode.HALF_UP);
		
		return bd.doubleValue();
	}

}
