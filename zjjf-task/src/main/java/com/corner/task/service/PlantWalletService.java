package com.corner.task.service;

import com.corner.task.beans.PlantWalletLog;
import com.corner.task.beans.SpWalletLog;
import com.corner.task.beans.msg.ModelMsg;


public interface PlantWalletService {
	/**
	 * 
	* @Title: updatePlantWalletAndLog 
	* @Description: 更新平台钱包金额、同时添加日志
	* @param: @param plantWalletLog
	* @param: @return
	* @param: @throws Exception	设定文件 
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	ModelMsg updatePlantWalletAndLog(PlantWalletLog plantWalletLog) throws Exception;
	
	/**
	 * 
	* @Title: updateSupplerWalletAndLog 
	* @Description:  更新批发商钱包金额、同时添加日志 
	* @param: @param spWalletLog
	* @param: @return
	* @param: @throws Exception	设定文件 
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	ModelMsg updateSupplerWalletAndLog(SpWalletLog spWalletLog) throws Exception;
}
