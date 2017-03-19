package com.corner.task.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Properties;

public class PropertiesCacheUtil {
	/**系统properties文件**/
	public static final String SYSTEM = "config/system/system.properties";
	/**system-url**/
	public static final String SYSTEM_URL = "url";
	/**取消订单接口**/
	public static final String SYSTEM_SPORDERREVOKE = "spOrderRevoke";
	/**登陆默认地址**/
	public static final String SYSTEM_DEFAULTLOGINMAIN = "defaultLoginMain";


	public static String getValue(String tipKey, String tip) {
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadAllProperties(tip);
			if( props != null && props.size()>0){
				return props.getProperty(tipKey);
			}
			return "";
		} catch (Exception e) {
			return "";
		}
	}

	
}

