/**
 * 
 */
package com.corner.core.utils;

import java.text.MessageFormat;

public class HQLUtil {

	public static String getLikeStr(String value){
		return MessageFormat.format("%{0}%", value);
	}
}
