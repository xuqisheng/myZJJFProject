package com.corner.scms.service;

import com.corner.core.beans.Region;
import com.corner.core.beans.SpWalletLog;
import com.corner.core.beans.SystemInfo;

import java.util.List;

public interface PublicService extends BaseService {
	List<Region> getRegions();
	
	SystemInfo getSystemInfoById(String id);

	/**
	 * 根据上级的id获取所有下级区域
	* @Title
	* @Description: TODO 
	* @param @param pid
	* @param @return
	* @2016年7月2日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public List<Region> getRetionByPid(Integer pId);
}
