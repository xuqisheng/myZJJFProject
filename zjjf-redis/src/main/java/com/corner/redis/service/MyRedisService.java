package com.corner.redis.service;

import com.corner.redis.beans.User;

public interface MyRedisService {

	void putStringValue(String key, String value);

	String getStringValue(String key);
	
	void setRedisUser(String key,User u);
	
	User getRedisUser(String key);

}
