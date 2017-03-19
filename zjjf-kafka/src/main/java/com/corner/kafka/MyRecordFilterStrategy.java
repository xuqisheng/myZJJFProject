package com.corner.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

/**
 * 
* @ClassName: MyRecordFilterStrategy 
* @Description: TODO(该类主要处理消息是否被重复处理，ackDiscarded 标示是否像zookeeper提交已被处理的记录) 
* @author 铁中棠  tiezhongtang@izjjf.cn 
* @date 2016年9月29日 下午6:09:41 
*
 */
public class MyRecordFilterStrategy implements RecordFilterStrategy<Integer,String> {

	@Override
	public boolean filter(ConsumerRecord<Integer,String> consumerRecord) {
		return false;
	}

}

