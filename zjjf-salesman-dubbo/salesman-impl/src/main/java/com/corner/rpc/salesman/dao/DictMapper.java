package com.corner.rpc.salesman.dao;

import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.Dict;

public interface DictMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
    
	/**
	 * 根据数据字典中的类型，获取对应数据列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getDictListByType(String type);
    
    /**
     * 获取字典配置过滤部门编码列表信息（供过滤签到非业务员部门使用）
     * @return
     */
    public List<String> getFilterDeptList();
}