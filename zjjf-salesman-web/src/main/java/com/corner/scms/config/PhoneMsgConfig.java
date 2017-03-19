/**   
* @Title: PhoneMsgConfig.java 
* @Package com.corner.scms.config 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月18日 下午4:34:26 
* @version V1.0   
*/

package com.corner.scms.config;

import com.corner.core.config.PropertieNameConts;
import com.corner.core.utils.PropertiesCacheUtil;

/** 
* @ClassName: PhoneMsgConfig 
* @Description:短信配置类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月18日 下午4:34:26 
*  
*/

public class PhoneMsgConfig {

	public static String weburl = PropertiesCacheUtil.getValue("weburl", PropertieNameConts.System);

	public static String apikey = PropertiesCacheUtil.getValue("apikey", PropertieNameConts.System);

	public static String username = PropertiesCacheUtil.getValue("username", PropertieNameConts.System);

	public static String password = PropertiesCacheUtil.getValue("password", PropertieNameConts.System);
	
	public static int retrySeconds =Integer.parseInt(PropertiesCacheUtil.getValue("retrySeconds", PropertieNameConts.System));
	
	public static int retryTimes =Integer.parseInt(PropertiesCacheUtil.getValue("retryTimes", PropertieNameConts.System));
}
