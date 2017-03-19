/**  
 * @Title: package-info.java
 * @Package com.corner.mobile.common.utils.http
 * @Description: TODO(用一句话描述该文件做什么)
 * @author luke    
 * @email   luke@mibodoctor.com  
 * @date 2015年3月8日 下午4:43:20
 * @version V1.0  
 */
/**
 * @ClassName: package-info
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author luke
 * @email   luke@mibodoctor.com  
 * @date 2015年3月8日 下午4:43:20
 *
 */
package com.corner.core.utils.http;

import com.corner.core.config.PropertieNameConts;
import com.corner.core.utils.PropertiesCacheUtil;


public class HttpEVConfig{
	
	public static String HTTP_CHARSET="utf-8";
	
	public static String HTTP_GET="GET";
	
	public static String HTTP_POST="POST";

	public static int  CM_MaxTotal=Integer.parseInt( PropertiesCacheUtil.getValue("CM_MaxTotal", PropertieNameConts.HTTP));
	
	public static int  CM_MaxPerRoute=Integer.parseInt( PropertiesCacheUtil.getValue("CM_MaxPerRoute", PropertieNameConts.HTTP));

}