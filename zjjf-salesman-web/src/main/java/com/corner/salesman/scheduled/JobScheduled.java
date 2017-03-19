package com.corner.salesman.scheduled;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.corner.salesman.model.ShopInfo;
import com.corner.salesman.service.ShopInfoService;

/**
 *任务调度类
 *
 * @author longxian 2014/8/16 17:46
 */
@Component
@Lazy(false)
public class JobScheduled {
	
	private static Logger logger = LoggerFactory.getLogger(JobScheduled.class);
	
	@Autowired
	private ShopInfoService shopInfoService;
	
    public static SimpleDateFormat sdf_yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
    
    /**
     * "0 15 10 * * ? *" 2016上午10:15触发
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void initShopData() {
        try{
       		logger.info("==============同步客户数据任务调度开始===========");
        	ShopInfo shopVO = new ShopInfo();
  		  	new Thread(new SyncShop(shopVO)).start(); 
  		  	logger.info("==============同步客户数据任务调度结束===========");
  	   }catch (Exception e) {
  		  e.printStackTrace();
  		logger.error("同步客户数据异常：{}",e.getMessage());
  	   }
    }
    
    /**
     * 每两个小时
     */
    @Scheduled(cron = "0 0 */2 * * *")
    public void exejob() {
       try{
       		logger.info("==============同步客户数据任务调度开始===========");
        	ShopInfo shopVO = new ShopInfo();
        	shopVO.setQueryType("CURDATE");
  		  	new Thread(new SyncShop(shopVO)).start(); 
  		  	logger.info("==============同步客户数据任务调度结束===========");
  	   }catch (Exception e) {
  		  e.printStackTrace();
  		logger.error("同步客户数据异常：{}",e.getMessage());
  	   }
    }
    
	class SyncShop implements Runnable { // 实现了Runnable接口，jdk就知道这个类是一个线程  
		
		private ShopInfo shopVO;
		public SyncShop(){
			
		}
		public SyncShop(ShopInfo shopVO){
			this.shopVO =  shopVO;
		}
		
	    public void run() {  
	    	try {
				shopInfoService.batchSyncShop(shopVO);
			} catch (Exception e) {
				logger.error("多线程同步店铺过程中出现异常：{}",e);
				e.printStackTrace();
			}
	    }  
	}
    
}