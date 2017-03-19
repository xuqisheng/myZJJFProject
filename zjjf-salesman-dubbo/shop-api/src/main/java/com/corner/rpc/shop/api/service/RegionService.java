package com.corner.rpc.shop.api.service;

import java.util.HashMap;
import java.util.List;

import com.corner.rpc.shop.model.Region;
import com.corner.rpc.shop.model.RegionVo;

/**
 * 区域服务接口
 * @author Administrator
 *
 */
public interface RegionService {

	/**
	 * 根据条件获取区域列表信息（根据PID获取省、市、区数据列表）
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public List<Region> queryRegionList(Region region) throws Exception;
	
	/**
	 * 查询app端需要的省市区列表信息
	 * @return
	 * @throws Exception
	 */
	public List<RegionVo> getAllEnableRegionList() throws Exception;
	
	public List<HashMap<String,String>> getEnableRegionCache() throws Exception;
	
	/**
	 * 根据省市区获取对应的区域编码在拼接返回
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public String getRegionStr(Integer provinceId,Integer cityId,Integer areaId) throws Exception;
}
