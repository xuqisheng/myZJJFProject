/*package org.andy.shop.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.JSON;
import com.corner.salesman.common.utils.SpringContextHolder;
import com.corner.salesman.model.UserInfo;
import com.corner.salesman.service.UserInfoService;
import com.corner.salesman.service.UserService;

*//**
 * 创建时间：2015-1-27 下午10:45:38
 * 
 * @author andy
 * @version 2.2
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:spring-mybatis.xml" ,"classpath:spring-jedis.xml"})
public class TestUserService {

	private static final Logger LOGGER = Logger
			.getLogger(TestUserService.class);

	@Autowired
	private UserInfoService userService;

	
	 * @Test public void testQueryById() { ApplicationContext ac = new
	 * ClassPathXmlApplicationContext( new String[] { "spring.xml",
	 * "spring-mybatis.xml" }); UserService userService = (UserService)
	 * ac.getBean("userService"); UserInfo userInfo =
	 * userService.getUserById(1); System.out.println(userInfo.getUname()); }
	 
	
	@Test
	public void testQueryById5() {
		ApplicationContext ac = new
				  ClassPathXmlApplicationContext( new String[] { "spring.xml","spring-mybatis.xml","classpath:spring-jedis.xml" });
//		JedisPool jedisPool = (JedisPool)ac.getBean("jedisPool");
		JedisPool jedisPool = SpringContextHolder.getBean(JedisPool.class);
		System.err.println("99999999999");
		System.err.println(jedisPool.getResource().get("longxian"));
		System.err.println("99999999999");
	}

	@Test
	public void testQueryById1() {
		UserInfo userInfo = userService.getUserById(1);
		LOGGER.info(JSON.toJSON(userInfo));
	}

	@Test
	public void testQueryAll() {
		List<UserInfo> userInfos = userService.getUsers();
		LOGGER.info(JSON.toJSON(userInfos));
	}

	@Test
	public void testInsert() {
		
		for (int i = 0; i < 80; i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUname("xiaoming");
			userInfo.setUnumber(5);
			int result = userService.insert(userInfo);
			System.out.println(result);
		}
		
	}
}
*/