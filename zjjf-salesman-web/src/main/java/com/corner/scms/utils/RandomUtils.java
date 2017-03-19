/**   
* @Title: RandomUtils.java 
* @Package com.corner.scms.utils 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月18日 下午4:02:21 
* @version V1.0   
*/

package com.corner.scms.utils;

import java.util.Random;

/**
 * @ClassName: RandomUtils
 * @Description:生成n位随机数工具类
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月18日 下午4:02:21
 * 
 */

public class RandomUtils {

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
	
	public static void main(String[] args) {
		System.out.println(RandomUtils.getRandNum(6));
	}
}
