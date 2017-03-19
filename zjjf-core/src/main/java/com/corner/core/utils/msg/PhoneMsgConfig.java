package com.corner.core.utils.msg;

import com.corner.core.config.PropertieNameConts;
import com.corner.core.utils.PropertiesCacheUtil;

public class PhoneMsgConfig {

	public static String weburl = PropertiesCacheUtil.getValue("weburl", PropertieNameConts.System);

	public static String apikey = PropertiesCacheUtil.getValue("apikey", PropertieNameConts.System);

	public static String username = PropertiesCacheUtil.getValue("username", PropertieNameConts.System);

	public static String password = PropertiesCacheUtil.getValue("password", PropertieNameConts.System);
	
	public static int retrySeconds =Integer.parseInt(PropertiesCacheUtil.getValue("retrySeconds", PropertieNameConts.System));
	
	public static int retryTimes =Integer.parseInt(PropertiesCacheUtil.getValue("retryTimes", PropertieNameConts.System));
	
	//通知类短信接口参数
    public static String apikeyyx = PropertiesCacheUtil.getValue("apikeyyx", PropertieNameConts.System);
    public static String usernameyx = PropertiesCacheUtil.getValue("usernameyx", PropertieNameConts.System);
    public static String passwordyx = PropertiesCacheUtil.getValue("passwordyx", PropertieNameConts.System);

}
