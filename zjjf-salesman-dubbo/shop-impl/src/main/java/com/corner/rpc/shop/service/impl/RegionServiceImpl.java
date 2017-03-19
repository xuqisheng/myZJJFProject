package com.corner.rpc.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.shop.api.service.RegionService;
import com.corner.rpc.shop.dao.RegionMapper;
import com.corner.rpc.shop.model.Region;
import com.corner.rpc.shop.model.RegionVo;

@Service("regionService")
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionMapper regionMapper;
	
	@Override
	public List<Region> queryRegionList(Region region) throws Exception {
		return regionMapper.queryRegionList(region);
	}
	
	/**
	 * 查询app端需要的省市区列表信息
	 * @return
	 * @throws Exception
	 */
	public List<RegionVo> getAllEnableRegionList() throws Exception{
		return regionMapper.getAllEnableRegionList();
	}
	
	//获取生成的店铺编号字符串
	@Override
	public String getRegionStr(Integer provinceId,Integer cityId,Integer areaId) {
		Region sheng = regionMapper.selectByPrimaryKey(provinceId);
		Region shi = regionMapper.selectByPrimaryKey(cityId);
		Region region = regionMapper.selectByPrimaryKey(areaId);
		StringBuilder str = new StringBuilder("");
		String shengStr= "";
		if(sheng != null && sheng.getRegionCode() != null){
			shengStr = sheng.getRegionCode().toString();
		}
		String shiStr= "";
		if(shi != null && shi.getRegionCode() != null){
			shiStr = shi.getRegionCode().toString();
		}
		String regionStr= "";
		if(region != null && region.getRegionCode() != null){
			regionStr = region.getRegionCode().toString();
		}
		str.append(shengStr).append(shiStr).append(regionStr);
		return str.toString();
	}

	/**
	 * 获取全部有效的区域信息（供excel导入的时候使用）
	 */
	@Override
	public List<HashMap<String, String>> getEnableRegionCache() throws Exception {
		return regionMapper.getEnableRegionCache();
	}

}
