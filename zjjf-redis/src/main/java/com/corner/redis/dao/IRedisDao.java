package com.corner.redis.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.RedisOperations;


/** 
 * 
* @ClassName: IRedisDao 
* @Description: TODO(泛型接口) 
* @author 铁中棠  tiezhongtang@izjjf.cn 
* @date 2016年4月8日 下午5:57:30 
* 
* @param <K>
* @param <V>
 */
public interface IRedisDao<K,V> extends RedisOperations<K, V> {
		
	void setComValue(K key,V value);
	
	void setComValue(K key,V value,Long offset);
	
	V getComValue(K key);

	void setListComValue(K key, List<V> listValue);

	List<V> getListComValue(K key);
	
	void setSetComValue(K key, Set<V> setValue);

	Set<V> getSetComValue(K key);
	
	void setMapComValue(K key, Map<K,V> mapValue);

	Map<K,V> getMapComValue(K key);
}