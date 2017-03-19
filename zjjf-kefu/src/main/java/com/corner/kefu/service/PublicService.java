package com.corner.kefu.service;

import java.util.List;

import com.corner.core.beans.Region;

public interface PublicService extends BaseService {
	List<Region> getRegions();
	
	List<Region> findRegionByPId(String pId);
	
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
