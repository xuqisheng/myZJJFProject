package com.corner.core.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
	/**
	 * 随机6个数
	 * @author Dick 2015年2月10日
	 * @Email  823882651@qq.com
	 * @Desc
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomNum(int max) {
		List<Integer> randomlist = new ArrayList<Integer>();
		Random ran = new Random();
		int res;
		while (true) {
			res = ran.nextInt(max);
			if (randomlist.contains(res)) {
				continue;
			} else {
				return res;
			}
		}
	}
}
