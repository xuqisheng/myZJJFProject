package com.zjjf.analysis.mapper.analysis;

import java.util.HashMap;
import java.util.List;

import com.zjjf.analysis.beans.analysis.base.BaseRoleData;

public interface BaseRoleDataMapper {
	
    int deleteByRoleId(Integer id);

    int insert(BaseRoleData record);

    int insertSelective(BaseRoleData record);

    BaseRoleData selectByPrimaryKey(Integer id);

    void deleteByMenuIdAndRoleId(HashMap<String, Object> deletedataParam);

    int updateByPrimaryKey(BaseRoleData record);
    
    List<BaseRoleData> getAuthorityData(HashMap<String, Object> dataParam);
	
	void update(BaseRoleData record);
	
	List<BaseRoleData> getBy_datatemplateId_menuId(HashMap<String, Object> dataParam);
	
	List<Integer> getMenuIdList(Integer roleId);
}