package com.corner.salesman.commons.utils;

/**
 * 创建序列
 * @author yuanbao
 * @ClassName: GenerateSequence 
 * @Description: TODO
 * @date 2016年5月16日 下午7:50:12 
 * @author 元宝@izjjf.cn
 */
public class GenerateSequence {

	public static String getSequence(Integer num,Integer num1){
		  if(num == null){
				return ""; 
		  }
		  num+=1;
		  char[] ary1 = num.toString().toCharArray();
		  if(num1 == null){
			  num1=4;
		  }
		  char[] ary2 = new char[num1];
		  for (int i = 0; i < num1; i++) {
			  ary2[i]='0';
		  }
		  System.arraycopy(ary1, 0, ary2, ary2.length-ary1.length, ary1.length);
		  String result = new String(ary2);
		  return result;
	}
}
