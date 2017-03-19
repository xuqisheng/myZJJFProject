/**   
* @Title: PCSavePushMsgUtil.java 
* @Package com.corner.pc.web.controller.util 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn   
* @date 2015年12月16日 上午11:10:58 
* @version V1.0   
*/
package com.corner.kefu.utils;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: PCSavePushMsgUtil 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn 
 * @date 2015年12月16日 上午11:10:58  
 */
@Component
public class PCSavePushMsgUtil {
	@Autowired
	ThreadPoolTaskExecutor taskExecutor;

	public void savePushMsg(Map<String, Object> map) {
	    
		taskExecutor.execute(new SavePushMsgRunnable(map));
	}
}
