package com.corner.kefu.dao;

import java.util.List;

import com.corner.kefu.beans.ro.ConfigRo;
import com.corner.kefu.beans.vo.ConfigVo;

public interface ConfigMgMapper {
	
	
	/**
	 * 获取所有系统配置信息 
	* @Title
	* @Description: TODO 
	* @param @return
	* @2016年4月14日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public List<ConfigVo> getAllConfig(ConfigRo configRo);
	
	public int getAllConfigCount(ConfigRo configRo);

	/**
	 * 
	* @Title: deleteFromDeliveryConfig 
	* @Description:清空DeliveryConfig
	* @param 
	* @return void    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	public void deleteFromDeliveryConfig();
}
