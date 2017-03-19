package com.corner.kefu.dao;

import java.util.Map;

import com.corner.kefu.beans.ro.ConfigPayRo;
import com.corner.kefu.beans.vo.ConfigPayVo;

public interface ConfigPayMgMapper {
	
	
	/**
	 * 根据id获取支付方式
	* @Title
	* @Description: TODO 
	* @param @param systemConfigDetailRo
	* @param @return
	* @2016年4月14日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public ConfigPayVo getSystemConfigDetailById(ConfigPayRo configPayRo);

	/**
	 * 查询支付方式是否存在
	* @Title
	* @Description: TODO 
	* @param @param map
	* @param @return
	* @2016年4月15日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public int getSystemConfigDetailByPayType(Map<String, Object> map);

	/**
	 * 更具支付方式修改
	* @Title
	* @Description: TODO 
	* @param @param systemConfigDetailRo
	* @2016年4月15日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public void updateSystemConfigDetailByPayType(ConfigPayRo configPayRo);
	

}
