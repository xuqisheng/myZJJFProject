package com.corner.rpc.salesman.dao;

import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.Dept;

public interface DeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
    
	/**
	 * 获取部门列表信息
	 * @param record
	 * @return
	 */
    public List<Map<String,Object>> getDeptLevelHashMap(Dept record);
    
    /**
     * 获取全部有效的部门信息列表
     * @return
     */
    public List<Dept> getDeptmentAllList();
}