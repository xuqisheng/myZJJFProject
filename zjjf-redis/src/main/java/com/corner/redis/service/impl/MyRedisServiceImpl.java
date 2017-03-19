package com.corner.redis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.redis.beans.User;
import com.corner.redis.dao.IStringRedisDao;
import com.corner.redis.dao.IUserRedisDao;
import com.corner.redis.service.MyRedisService;

@Service
public class MyRedisServiceImpl implements MyRedisService {

	@Autowired
	IStringRedisDao<String,String> stringRedisDao;
	
	@Autowired
	IUserRedisDao<String,User> userRedisDao;
	
	@Override
	public void putStringValue(String key,String value){
		stringRedisDao.setComValue(key, value);
	}
	
	@Override
	public String getStringValue(String key){
		return stringRedisDao.getComValue(key);
	}

	@Override
	public void setRedisUser(String key, User u) {
		userRedisDao.setComValue(key, u);
	}

	@Override
	public User getRedisUser(String key) {
		return userRedisDao.getComValue(key);
	}
	
}
