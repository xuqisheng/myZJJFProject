package com.corner.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-kafka-customer.xml" })
public class TestConsumer {
	
	protected static Logger logger = LoggerFactory.getLogger(TestConsumer.class);
	
	//@Autowired
	//PojoConsumer pojoConsumer;
	
	@Test
	public void kafkaTest() {
		try {
			//pojoConsumer.setDefaultTopic("dddd");
			//logger.info("sleep kaishi:"+pojoConsumer.getDefaultTopic());			
			Thread.sleep(30000);//30秒
			logger.info("sleep jieshu");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
