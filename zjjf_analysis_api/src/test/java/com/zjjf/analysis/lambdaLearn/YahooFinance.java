package com.zjjf.analysis.lambdaLearn;

import java.math.BigDecimal;

public class YahooFinance {
	
	public static BigDecimal getPrice(final String ticker) {
		try {
//			final URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s=" + ticker);
//			final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
//			final String data = reader.lines().skip(1).findFirst().get();
			final String[] dataItems = {"100" , "200"};
			return new BigDecimal(dataItems[dataItems.length - 1]);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}
