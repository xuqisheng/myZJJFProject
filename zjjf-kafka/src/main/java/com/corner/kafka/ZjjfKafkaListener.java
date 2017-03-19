package com.corner.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

public class ZjjfKafkaListener {

	protected static Logger logger = LoggerFactory.getLogger(ZjjfKafkaListener.class);

	@KafkaListener(id = "listenFormykafkaP1Re3", topics = "mykafka.p1.re3")
	public void listenFormykafkaP1Re3(ConsumerRecord<Integer, String> record, Acknowledgment acknowledgment) {
		logger.info("recive kafka msg in listenFormykafkaP1Re3");
		logger.info("recive kafka msg topic:    "+record.topic());
		logger.info("recive kafka msg partition:"+record.partition());
		logger.debug("recive kafka msg key:     "+record.key());
		logger.info("recive kafka msg data:     "+record.value());
		boolean dealSuccess = true;
		//处理业务逻辑
		if(dealSuccess){
			acknowledgment.acknowledge();
		}else{
			//做其他处理
		}
	}
	
	@KafkaListener(id = "listenFormykafkaP4Re3", topics = "mykafka.p4.re3")
	public void listenFormykafkaP4Re3(ConsumerRecord<Integer, String> record, Acknowledgment acknowledgment) {
		logger.info("recive kafka msg in listenFormykafkaP1Re3");
		logger.info("recive kafka msg topic:    "+record.topic());
		logger.info("recive kafka msg partition:"+record.partition());
		logger.debug("recive kafka msg key:     "+record.key());
		logger.info("recive kafka msg data:     "+record.value());
		boolean dealSuccess = true;
		//处理业务逻辑
		if(dealSuccess){
			acknowledgment.acknowledge();
		}else{
			//做其他处理
		}
	}

}