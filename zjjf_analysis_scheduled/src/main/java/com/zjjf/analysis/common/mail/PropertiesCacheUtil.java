package com.zjjf.analysis.common.mail;

import java.util.Properties;

import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PropertiesCacheUtil {


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

