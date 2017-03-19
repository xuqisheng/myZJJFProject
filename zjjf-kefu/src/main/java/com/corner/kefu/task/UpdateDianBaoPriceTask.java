package com.corner.kefu.task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.corner.core.beans.SystemInfo;
import com.corner.core.utils.DateUtil;
import com.corner.kefu.service.SystemInfoService;
import com.corner.kefu.service.sp.SpPlantItemService;

@Component
public class UpdateDianBaoPriceTask {
	private static Logger logger = LoggerFactory.getLogger(UpdateDianBaoPriceTask.class);

	@Autowired
	SystemInfoService  systemInfoService;
	@Autowired
	SpPlantItemService spPlantItemService;
	

	//@Scheduled(cron = "0 15,45 * * * ?")
	/*@Scheduled(cron = "0 36 14 * * ?")*/
    //@Scheduled(cron = "0 0/10 * * * ?")
	public void setVoucherExpire() {
		try {
			logger.info("====================这里更新店宝价格任务，每天的整点执行====================");
			SystemInfo systemInfo = systemInfoService.getSystemInfo("KDB_Price_Task");
			if(systemInfo!=null && !StringUtils.isEmpty(systemInfo.getContent())){
				Date taskTime=DateUtil.StringToDateSimple(systemInfo.getContent());
				if(taskTime ==null){
					logger.info("《更新店宝价格任务》定时任务执行失败，日期格式错误：{}",systemInfo.getContent());
					return;
				}
				Calendar taskCal = new GregorianCalendar();
				taskCal.setTime(taskTime);
				Calendar calendarNow = Calendar.getInstance();
				Long interval = calendarNow.getTimeInMillis()-taskCal.getTimeInMillis();
				if(interval>0 && interval<1800000){//时间节点在半小时以内
				  spPlantItemService.updatePriceTask();
				}
				logger.info("更新店宝价格任务》结束");
			}
		} catch (Exception e) {
			logger.error("《更新店宝价格任务》异常：",e);
			
		}
	}
	
}
