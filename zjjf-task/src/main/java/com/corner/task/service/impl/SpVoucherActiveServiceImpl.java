package com.corner.task.service.impl;


import com.corner.kefu.service.sp.SendVoucherService;
import com.corner.task.beans.SpVoucherActive;
import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.dao.mg.SpVoucherActiveMgMapper;
import com.corner.task.service.SpVoucherActiveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpVoucherActiveServiceImpl implements SpVoucherActiveService{

	private static Logger logger = LoggerFactory.getLogger(SpVoucherActiveServiceImpl.class);
	@Autowired
	SpVoucherActiveMgMapper spVoucherActiveMgMapper;

	@Autowired
    SendVoucherService sendVoucherService;
	
	public ModelMsg updateSpVoucherActive() {
		try {
			logger.info("▇▇▇▇▇▇▇定时开启/结束活动begin▇▇▇▇▇▇▇▇▇");
			//SpVoucherActiveMgMapper.updateBatchSpVoucherActive();
			//结束所有到期活动
			List<SpVoucherActive> list = spVoucherActiveMgMapper.getAllFinishActive();
			if(list!=null&&list.size()!=0){
				//处理SpVoucherActiveMiddle相关表
				spVoucherActiveMgMapper.updateFinishActive(list);
				spVoucherActiveMgMapper.updateAllFinishActive();
			}
			for (SpVoucherActive active :
					list) {
				if (active.getRuleType().intValue() == 14) {
					sendVoucherService.sendVoucher(active.getId());
				 }
				}
			//开启所有要开启的活动
			spVoucherActiveMgMapper.updateStartAllActive();
			logger.info("▇▇▇▇▇▇▇定时开启/结束活动end▇▇▇▇▇▇▇▇▇");
			return new ModelMsg(true, "定时改变活动状态结束！");
		} catch (Exception e) {
			logger.error("定时结束活动出错:",e);
			return new ModelMsg(false, "定时改变活动状态出错:"+e.toString());
		}
	}
}
