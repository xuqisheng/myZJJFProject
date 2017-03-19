package com.corner.kefu.service;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Region;
import com.corner.kefu.beans.vo.sp.RegionVo;

public interface RegionService extends BaseService{

	
	/**
	 * 
	* @Title: getAllSupplierRegionList 
	* @Description:查询所有批发商所在的区域列表
	* @param @return
	* @return List<RegionVo>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<RegionVo> getAllSupplierRegionList() throws Exception;

	List<Region> getRegionByPidOrRegionLevel(Map<String, Object> map);

}
