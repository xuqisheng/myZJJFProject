package com.corner.redis.dao.impl;

import com.corner.redis.beans.User;
import com.corner.redis.dao.IUserRedisDao;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author LKH
 * 该类演示如何操作简单普通pojo的值的存储
 */
@Repository
public class UserRedisDao extends AbstractBaseRedisDao<String, User> implements IUserRedisDao<String, User> {

	@Override
	public RedisSerializer<String> getObjectKeySerializer() {
		return AbstractBaseRedisDao.stringJackSonSerializer;
	}

	@Override
	public RedisSerializer<User> getObjectSerializer() {
		return new Jackson2JsonRedisSerializer<User>(User.class);
	}


}