package com.corner.account.service;

import java.math.BigDecimal;

import com.corner.core.beans.MaWithDraw;
import com.corner.core.beans.PlantWalletLog;
import com.corner.core.beans.SpWalletLog;
import com.corner.core.beans.WhWithDraw;
import com.corner.core.beans.msg.ModelMsg;


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
	* @Title: findWhWallet 
	* @Description: 获取仓库钱包金额
	* @param: @param id
	* @param: @return	设定文件 
	* @return BigDecimal    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	BigDecimal findWhWalletById(String id);
	/**
	 * 
	 * @Title: findWhWallet 
	 * @Description: 获取经销商钱包金额
	 * @param: @param id
	 * @param: @return	设定文件 
	 * @return BigDecimal    返回类型 
	 * @author 孟星魂	mengxinghun@izjjf.cn
	 */
	BigDecimal findMaWalletById(String id);
	
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
	
	/**
	 * 
	* @Title: insertWhWithDraw 
	* @Description: 仓库提现操作
	* @param: @param whWithDraw
	* @param: @return
	* @param: @throws Exception	设定文件 
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	ModelMsg insertWhWithDraw(WhWithDraw whWithDraw) throws Exception;
	
	/**
	 * 
	* @Title: insertWhWithDraw 
	* @Description: 经销商提现操作
	* @param: @param whWithDraw
	* @param: @return
	* @param: @throws Exception	设定文件 
	* @return ModelMsg    返回类型 
	* @author 孟星魂	mengxinghun@izjjf.cn
	 */
	ModelMsg insertMaWithDraw(MaWithDraw maWithDraw) throws Exception;
}
