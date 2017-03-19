package com.zjjf.analysis.services.ajie;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjjf.analysis.producer.ajie.IAjieProducer;

@Service(version="1.0.0")
public class AjieProducerImpl implements IAjieProducer{

	@Override
	public String sengMsg(String msg) {
		System.out.println(msg);
		return "hello " + msg;
	}

}
