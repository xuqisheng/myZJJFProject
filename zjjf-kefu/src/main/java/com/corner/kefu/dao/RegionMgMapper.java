package com.corner.kefu.dao;

import java.util.List;
import java.util.Map;

import com.corner.core.beans.Region;
import com.corner.kefu.beans.ro.StoreMgRo;


/**
 * 
* @ClassName: RegionMgMapper 
* @Description: 地区表信息
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2016年1月8日 下午2:51:51 
*
 */
public interface RegionMgMapper {
	List<Region> findRegionByPId(String pId);
	int updateRegionHasStore(Map<String, Object> map);
	
	/**
	 * 
	* @Title: getAllSupplierRegionList 
	* @Description:获取所有批发商所在区域的集合
	* @param @return
	* @return List<Region>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<Region> getAllSupplierRegionList();
	
	List<Region> getRegionByPidOrRegionLevel(Map<String, Object> map);
	/**
	 * 
	* @Title: getStoreRegion 
	* @Description:获取店铺的省市区信息
	* @param @param storeMgRo
	* @param @return
	* @return List<Region>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<Region> getStoreRegion(StoreMgRo storeMgRo);
	
	List<Region> getAllRetionByPid(Map<String, Object> map);
	
	/**
	 * 
	* @Title: getRegionListByProvinceId 
	* @Description:通过省id获取所有下属区集合
	* @param @param regionId
	* @param @return
	* @return List<Region>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<Region> getRegionListByProvinceId(Integer regionId);
	/**
	 * 
	* @Title: getRegionListByCityId 
	* @Description:通过市id获取所有下属区集合
	* @param @param regionId
	* @param @return
	* @return List<Region>    返回类型
	*@author 杨开泰 yangkaitai@izjjf.cn
	* @throws
	 */
	List<Region> getRegionListByCityId(Integer regionId);
}
