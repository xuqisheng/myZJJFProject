package com.corner.kefu.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 
* @ClassName: TimingUpdateSpVoucherAcitveTask 
* @Description:定时关闭已经结束的活动
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年5月5日 上午9:31:57 
*
 */

import com.corner.kefu.service.sp.SpVoucherActiveService;
@Component
public class TimingUpdateSpVoucherAcitveTask {

	private static Logger logger = LoggerFactory.getLogger(TimingUpdateSpVoucherAcitveTask.class);
	
	@Autowired
	SpVoucherActiveService spVoucherActiveService;
	
	
	//@Scheduled(cron = "0 1 0 * * ? ")//每天凌晨00:01分执行
	//@Scheduled(cron = "0 45 13 * * ? ")//每天凌晨00:01分执行
    //@Scheduled(cron = "0 0/30 * * * ?")
	public void updateSpVoucherActive() {
		try {
			logger.info("▇▇▇▇▇▇▇定时开启/结束活动begin▇▇▇▇▇▇▇▇▇");
			
			spVoucherActiveService.updateBatchSpVoucherActive();
			
			logger.info("▇▇▇▇▇▇▇定时开启/结束活动end▇▇▇▇▇▇▇▇▇");
		} catch (Exception e) {
			logger.error("定时结束活动出错:",e);
		}
	}
	
}
