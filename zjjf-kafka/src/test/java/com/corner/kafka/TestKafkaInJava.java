package com.corner.kafka;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-kafka-javaCfg.xml" })
public class TestKafkaInJava {
	
	//@Autowired
	//private MyListenerP1R3 listener;
	
	//@Autowired
	private KafkaTemplate<Integer, String> template;

	@Test
	public void testSimple() throws Exception {
		final CountDownLatch latch1 = new CountDownLatch(4);
		//waitListening("foo");
		template.send("mykafka.p1.re3", 0, "foossssdfasfegawegxxxx");
		//assertTrue(this.listener.latch1.await(10, TimeUnit.SECONDS));
	}



}
