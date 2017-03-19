package com.corner.redis.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.corner.redis.dao.IRedisDao;

/**
 * 
 * @author LKH
 * @param <K>
 * @param <V>
 */
public abstract class AbstractBaseRedisDao<K, V> extends RedisTemplate<K, V> implements IRedisDao<K, V> {

	protected static RedisSerializer<String> stringSerializer = new StringRedisSerializer();

	protected static RedisSerializer<String> stringJackSonSerializer = new Jackson2JsonRedisSerializer<String>(String.class);

	@Autowired
	private StringRedisTemplate redisTemplate;

	public AbstractBaseRedisDao() {
		setKeySerializer(getObjectKeySerializer());
		setValueSerializer(getObjectSerializer());
		setHashKeySerializer(getObjectKeySerializer());
		setHashValueSerializer(getObjectSerializer());
	}

	public abstract RedisSerializer<K> getObjectKeySerializer();

	public abstract RedisSerializer<V> getObjectSerializer();

	protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
		return new DefaultStringRedisConnection(connection);
	}

	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		setConnectionFactory(redisTemplate.getConnectionFactory());
		afterPropertiesSet();
	}

	@Override
	public void setComValue(K key, V value) {
		opsForValue().set(key, value);
	}
	
	@Override
	public void setComValue(K key, V value,Long offset) {
		opsForValue().set(key, value, offset);
	}

	@Override
	public V getComValue(K key) {
		return opsForValue().get(key);
	}

	@Override
	public void setListComValue(K key, List<V> listValue) {
		delete(key);
		if (null != listValue) {
			for (Iterator<V> iterator = listValue.iterator(); iterator.hasNext();) {
				opsForList().rightPush(key, iterator.next());
			}
		}
	}

	@Override
	public List<V> getListComValue(K key) {
		return opsForList().range(key, 0, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setSetComValue(K key, Set<V> setValue) {
		if (null != setValue) {
			for (Iterator<V> iterator = setValue.iterator(); iterator.hasNext();) {
				opsForSet().add(key, iterator.next());
			}
		}
	}

	@Override
	public Set<V> getSetComValue(K key) {
		return opsForSet().members(key);
	}
	
	@Override
	public void setMapComValue(K key, Map<K,V> setValue) {
		delete(key);
		opsForHash().putAll(key, setValue);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<K,V> getMapComValue(K key) {
		return (Map<K, V>) opsForHash().entries(key);
	}
	
}