package com.corner.rpc.shop.dao;

import java.util.HashMap;
import java.util.List;

import com.corner.rpc.shop.model.Region;
import com.corner.rpc.shop.model.RegionVo;

public interface RegionMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
    
    List<Region> queryRegionList(Region record);
    
    List<RegionVo> getAllEnableRegionList();
    
    List<HashMap<String,String>> getEnableRegionCache();
}