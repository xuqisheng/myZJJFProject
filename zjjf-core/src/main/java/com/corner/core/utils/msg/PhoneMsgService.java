/**  
 * @Title: package-info.java
 * @Package com.corner.mobile.common.utils.http
 * @Description: TODO(用一句话描述该文件做什么)
 * @author luke    
 * @email   luke@mibodoctor.com  
 * @date 2015年3月8日 下午4:43:20
 * @version V1.0  
 */
/**
 * @ClassName: package-info
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author luke
 * @email   luke@mibodoctor.com  
 * @date 2015年3月8日 下午4:43:20
 *
 */
package com.corner.core.utils.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class PhoneMsgService {

	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	
	public void sendMessage(String mobile, String content){
		taskExecutor.execute(new SendPhoneMsgRunnable(mobile, content));
	}

}