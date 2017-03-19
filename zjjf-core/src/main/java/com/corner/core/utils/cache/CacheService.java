/**  
 * @Title: package-info.java
 * @Package com.corner.mobile.common.utils.cache
 * @Description: TODO(用一句话描述该文件做什么)
 * @author luke    
 * @email   luke@mibodoctor.com  
 * @date 2015年3月7日 下午2:05:55
 * @version V1.0  
 */
/**
 * @ClassName: package-info
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author luke
 * @email   luke@mibodoctor.com  
 * @date 2015年3月7日 下午2:05:55
 *
 */
package com.corner.core.utils.cache;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class CacheService  {
	
	public static  final String SYSTEM_PHONE_MSG= "phoneMsgCache";
	
	public static  final String SYS_WX_ACCESS_TOKEN= "wxTicketCache";
	
	public static  final String ORDER_TEMP_CACHE= "orderTempCache";
	
	@Autowired
	CacheManager cacheManager;
	
	public Cache getCache(String name){
		return cacheManager.getCache(name);
	}
	
	public Collection<String> getCacheNames(){
		return cacheManager.getCacheNames();
	}
	
	/**************************************Cache 1 phoneMsgCache******************************************/
	
	public <T> T getValueFromPhoneMessageCache(Object key, Class<T> type){
		return cacheManager.getCache(SYSTEM_PHONE_MSG).get(key, type);
	}
	
	public void putValueInPhoneMessageCache(Object key, Object value){
		cacheManager.getCache(SYSTEM_PHONE_MSG).put(key, value);
	}
	
	/**************************************Cache 2 wxTicketCache******************************************/
	
	public String getWXTokenFromCache(){
		return cacheManager.getCache(SYS_WX_ACCESS_TOKEN).get("wxtoken", String.class);
	}

	public void putWXTokenToCache(String token){
		 cacheManager.getCache(SYS_WX_ACCESS_TOKEN).put("wxtoken", token);
	}

	public String getWXTicketFromCache() {
		return cacheManager.getCache(SYS_WX_ACCESS_TOKEN).get("wxticket", String.class);
	}

	public void putWXTicketToCache(String ticket) {
		cacheManager.getCache(SYS_WX_ACCESS_TOKEN).put("wxticket", ticket);
	}

	/**************************************Cache 3 wxTicketCache******************************************/
	
	public <T> T getValueFromOrderCache(Object key, Class<T> type){
		return cacheManager.getCache(ORDER_TEMP_CACHE).get(key, type);
	}
	
	public void putValueInOrderCache(Object key, Object value){
		cacheManager.getCache(ORDER_TEMP_CACHE).put(key, value);
	}
	
	public void removeValueFromOrderCache(Object key){
		cacheManager.getCache(ORDER_TEMP_CACHE).evict(key);
	}
	
}