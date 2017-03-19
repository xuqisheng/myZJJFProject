package com.corner.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-kafka-producter.xml" })
public class TestProducter {

	//@Autowired
	//ZjjfKafkaProducerService kafkaProducerService;
	
	@Test
	public void kafkaTest() {
		for (int i = 0; i < 100; i++) {
			//kafkaProducerService.sendMessage("mykafka.p4.re3", "msgdd"+i+" from eclipse");
			System.out.println("发送消息："+"msgdd"+i+" from eclipse");
			try {
				Thread.sleep(1000);//1秒
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
