package com.corner.scms.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.corner.core.pay.wx.config.WXConfig;
import com.corner.core.utils.JSONUtil;
import com.corner.core.utils.cache.CacheService;
import com.corner.core.utils.http.HttpEVConfig;
import com.corner.core.utils.http.HttpUtils;

@Component
public class AccessTokenServiceImp {
  
	@Autowired
	CacheService cacheService;
	
	public Map getOpenid(String code){
		if(StringUtils.isEmpty(code)){
			return null;
		}else{
			Map<String, String> params =new HashMap<String ,String>();
			params.put("grant_type", "authorization_code");
			params.put("appid", WXConfig.AppID);
			params.put("secret", WXConfig.AppSecret);
			params.put("code", code);
			try {
				String rest =HttpUtils.buildRequest(WXConfig.getOpenIdUrl, params,HttpEVConfig.HTTP_GET);
				Map map = JSONUtil.JSONToObject(rest, Map.class);
				return map;
			} catch (Exception e) {	
				e.printStackTrace();
				return  null;
			}
		}
	}
	
	public String getWXToken(HttpSession httpSession){
		String token = null;
		if(StringUtils.isEmpty(token)){
			Map<String, String> params =new HashMap<String ,String>();
			params.put("grant_type", "client_credential");
			params.put("appid", WXConfig.AppID);
			params.put("secret", WXConfig.AppSecret);
			StringBuilder sb = new StringBuilder(WXConfig.GetTockenUrl);
			sb.append("grant_type=client_credential");
			sb.append("&appid=");
			sb.append(WXConfig.AppID);
			sb.append("&secret=");
			sb.append(WXConfig.AppSecret);
			try {
				String rest =HttpUtils.buildRequest(WXConfig.GetTockenUrl, params,HttpEVConfig.HTTP_GET);
				Map map = JSONUtil.JSONToObject(rest, Map.class);
				String getToken = (String) map.get("access_token");
				if(StringUtils.isEmpty(getToken)){
					return  null;
				}else{
					httpSession.setAttribute("token", rest);
					return getToken;
				}
			} catch (Exception e) {	
				e.printStackTrace();
				return  null;
			}
		}
		return token;
	}
	
	/**
	 * {
		"errcode":0,
		"errmsg":"ok",
		"ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
		"expires_in":7200
		}
	 * @param httpSession 
	 */
	public String getWXTicket(HttpSession httpSession){
		String ticket =null;;
		if(StringUtils.isEmpty(ticket)){
			String token = getWXToken(httpSession);
			if(StringUtils.isEmpty(token)){
				return null;
			}else{
				Map<String, String> params =new HashMap<String ,String>();
				params.put("type", "jsapi");
				params.put("access_token",token);
				try {
					String rest = HttpUtils.buildRequest(WXConfig.GetTicktetUrl , params,HttpEVConfig.HTTP_GET);
					Map map = JSONUtil.JSONToObject(rest, Map.class);
					String getTicket = (String) map.get("ticket");
					if(StringUtils.isEmpty(getTicket)){
						return  null;
					}else{
						httpSession.setAttribute("ticket", rest);
						return getTicket;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}
		}else{
			return ticket;
		}

	}
	
	
}
