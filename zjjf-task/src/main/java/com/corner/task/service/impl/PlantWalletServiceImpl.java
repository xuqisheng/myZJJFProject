package com.corner.task.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.corner.task.beans.PlantWalletLog;
import com.corner.task.beans.SpWalletLog;
import com.corner.task.beans.msg.ModelMsg;
import com.corner.task.dao.mg.PlantWalletLogMgMapper;
import com.corner.task.dao.mg.SpWalletLogMgMapper;
import com.corner.task.service.PlantWalletService;
import com.corner.task.util.SpringContextHolder;
import com.corner.task.util.StringUtil;

@Service
public class PlantWalletServiceImpl implements PlantWalletService {
	private static Logger logger = LoggerFactory.getLogger(PlantWalletServiceImpl.class);
	@Override
	public ModelMsg updatePlantWalletAndLog(PlantWalletLog plantWalletLog) throws Exception {
		plantWalletLog.setActionTime(new Date());
		if(StringUtil.stringIsNullOrEmpty(plantWalletLog.getPlantWalletId()))
			return new ModelMsg(false , "缺少必要数据");
		else if(plantWalletLog.getMoney() == null || plantWalletLog.getOptType() == null || plantWalletLog.getActionType() ==null)
			return new ModelMsg(false , "缺少必要数据");
		int result = SpringContextHolder.getBean(PlantWalletLogMgMapper.class).updateWalletByLog(plantWalletLog);
		if(result == 0)
			throw new Exception("修改钱包失败");
		return new ModelMsg(true , "更新钱包成功");
	}
	
	@Override
	public ModelMsg updateSupplerWalletAndLog(SpWalletLog spWalletLog) throws Exception {
		spWalletLog.setActionTime(new Date());
		if(StringUtil.stringIsNullOrEmpty(spWalletLog.getSpId()))
			return new ModelMsg(false , "缺少必要数据");
		else if(spWalletLog.getMoney() == null || spWalletLog.getOptType() == null || spWalletLog.getActionType() ==null)
			return new ModelMsg(false , "缺少必要数据");
		int result = SpringContextHolder.getBean(SpWalletLogMgMapper.class).updateWalletByLog(spWalletLog);
		if(result == 0)
			throw new Exception("修改钱包失败");
		throw new Exception("修改钱包失败，未写");
	}
	
	
}
