package com.zjjf.analysis.lambdaLearn;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LambdaLearn {

	public static void main(String args[]) {

		// findHighPriced(Tickers.symbols.stream());
		List<String> list = Arrays.asList("a", "bbb", "c", "d", "bb");
		Stream<String> stream = list.stream();
		System.out.println(stream.map(LambdaLearn::getNum).filter(getFileter("b")).reduce(LambdaLearn::getReduce).get());
	}

	public static String getNum(final String ticker) {
		return ticker;
	}

	public static Predicate<String> getFileter(final String y) {
		return x -> x.contains(y);
	}

	public static String getReduce(final String x, final String y) {
		return x.contains(y) ? x : y;
	}

	public static void findHighPriced(final Stream<String> symbols) {
		final StockInfo highPriced = symbols.map(StockUtil::getPrice).filter(StockUtil.isPriceLessThan(500)).reduce(StockUtil::pickHigh).get();
		System.out.println("High priced under $500 is " + highPriced);
	}

}
