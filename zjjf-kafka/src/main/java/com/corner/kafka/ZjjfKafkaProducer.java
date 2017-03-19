package com.corner.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ZjjfKafkaProducer {

	protected static Logger logger = LoggerFactory.getLogger(ZjjfKafkaListener.class);
	
    @Autowired  
    private KafkaTemplate<Integer, String> kafkaTemplate;


	public void sendMessage(java.lang.String topic, String data) {
		logger.info("发送消息："+data);
        kafkaTemplate.setDefaultTopic(topic);
        kafkaTemplate.sendDefault(data);
	}

	public void sendMessage(java.lang.String topic, Integer key, String data) {
		logger.info("发送消息："+data+" key:"+key);
        kafkaTemplate.setDefaultTopic(topic);  
        kafkaTemplate.sendDefault(key, data);  
	}  

}