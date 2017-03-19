package com.corner.redis.dao.impl;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.corner.redis.dao.IStringRedisDao;

/**
 * 
 * @author LKH
 * 该类演示如何操作简单string值的存储
 *
 */
@Repository
public class StringRedisDao extends AbstractBaseRedisDao<String, String> implements IStringRedisDao<String, String> {
	
	@Override
	public RedisSerializer<String> getObjectKeySerializer() {
		return AbstractBaseRedisDao.stringSerializer;
	}

	@Override
	public RedisSerializer<String> getObjectSerializer() {
		return AbstractBaseRedisDao.stringSerializer;
	}


}