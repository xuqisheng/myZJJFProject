package com.corner.rpc.shop.dao;

import java.util.HashMap;
import java.util.List;

import com.corner.rpc.shop.model.SpGroup;

public interface SpGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SpGroup record);

    int insertSelective(SpGroup record);

    SpGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SpGroup record);

    int updateByPrimaryKey(SpGroup record);
    
    List<SpGroup> querySpGroupListByAreaId(Integer areaId);
    
    List<HashMap<String,String>> getEnableSpGroupCache();
    
    List<SpGroup> querySpGroupList(SpGroup record);
}