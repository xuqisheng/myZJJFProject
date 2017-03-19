package com.zjjf.analysis.common;

import java.math.BigDecimal;

public class NumberUtils {

	public static BigDecimal getIncrease_subtract(BigDecimal a, BigDecimal b) {
		if (BigDecimal.ZERO.equals(b) || b.doubleValue() == new BigDecimal(0.00).doubleValue()) {
			return new BigDecimal(0);
		} else {
			return a.subtract(b).multiply(new BigDecimal(100)).divide(b, 2, BigDecimal.ROUND_HALF_UP);
		}
	}
	
	public static BigDecimal getIncrease(BigDecimal a, BigDecimal b) {
		if (BigDecimal.ZERO.equals(b) || b.doubleValue() == new BigDecimal(0.00).doubleValue()) {
			return new BigDecimal(0);
		} else {
			return a.multiply(new BigDecimal(100)).divide(b, 2, BigDecimal.ROUND_HALF_UP);
		}
	}
}
