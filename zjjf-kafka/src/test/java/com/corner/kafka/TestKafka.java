package com.corner.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-kafka.xml" })
public class TestKafka {

	//@Autowired
	//KafkaConsumerService kafkaConsumerService;
	
	@Test
	public void kafkaTest() {
		try {
			System.out.println("sleep kaishi");
			Thread.sleep(1000000);
			
			System.out.println("sleep jieshu");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
