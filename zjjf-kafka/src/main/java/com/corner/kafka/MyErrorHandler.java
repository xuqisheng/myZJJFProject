package com.corner.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.ErrorHandler;

/**
 * 
* @ClassName: MyErrorHandler 
* @Description: TODO(消息处理异常的时候如何处理) 
* @author 铁中棠  tiezhongtang@izjjf.cn 
* @date 2016年9月29日 下午6:36:23 
*
 */

public class MyErrorHandler implements ErrorHandler {
	
	protected static Logger logger = LoggerFactory.getLogger(MyErrorHandler.class);

	@Override
	public void handle(Exception thrownException, ConsumerRecord<?, ?> data) {
		logger.error("Kafka消息处理异常",thrownException);
	}

}

