package com.corner.salesman.commons.utils.excel;

public class StringUtil {

	public static boolean hasValue(String val){
		boolean bool = false;
		if(null!=val&&!"".equals(val)){
			 bool = true;
		}
		return bool;
	}
}
