package com.corner.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * 产生订单编号方法
 * @author aimee at 2015年2月4日下午3:51:59
 * @email 1297579898@qq.com
 */
public class CreateOrderIdUtil {

	public static String dateToString() {
		String orderIdStr =new SimpleDateFormat("yyyyMMddHHmmssSSSS") .format(new Date());
		orderIdStr+=getRandNum(5);
		return orderIdStr;
	}
	
	
	public static String getRandNum(int charCount) {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			char c = (char) (randomInt(0, 10) + '0');
			charValue += String.valueOf(c);
		}
		return charValue;

	}

	public static int randomInt(int from, int to) {
		Random r = new Random();
		return from + r.nextInt(to - from);
	}
}
