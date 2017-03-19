package com.corner.salesman.mongodb.test;

import com.corner.salesman.commons.utils.JedisUtils;
import org.junit.Test;

public class RedisTest {

	@Test
	public void test1(){
		System.err.println("-----------------");
		//JedisUtils.set("banli", "测试缓存是否正常",100);
		//JedisUtils.setExpire("banli", 100);
		//System.err.println(JedisUtils.getTtl("banli"));
		/*String keyVal = "2016072000000";
		JedisUtils.set("banli", keyVal, 2000);
		
		keyVal = JedisUtils.get("banli");*/
		
		JedisUtils.incrBy("banli", 1L);
		
		System.err.println(JedisUtils.get("banli"));
	}
}
