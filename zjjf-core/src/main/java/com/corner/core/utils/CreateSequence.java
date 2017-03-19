package com.corner.core.utils;

/**
 * 创建序列
 * @author LONGWU-PC
 * @ClassName: createSequence 
 * @Description: TODO
 * @date 2016年5月16日 下午7:50:12 
 * @author 龙五  longwu@izjjf.cn
 */
public class CreateSequence {

	public static String getSequence(Integer number,Integer length){
		  if(number == null){
				return ""; 
		  }
		  number+=1;
		  char[] ary1 = number.toString().toCharArray();
		  if(length == null){
			  length=4;
		  }
		  char[] ary2 = new char[length];
		  for (int i = 0; i < length; i++) {
			  ary2[i]='0';
		  }
		  System.arraycopy(ary1, 0, ary2, ary2.length-ary1.length, ary1.length);
		  String result = new String(ary2);
		  
		  
		  return result;
	}
}
