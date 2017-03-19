package com.corner.rpc.salesman.api.service;

import java.util.List;
import java.util.Map;

import com.corner.rpc.salesman.model.Dept;

public interface DeptService {
	/**
	 * 获取部门层级列表数据
	 * @param record
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,Object>> getDeptLevelHashMap(Dept record) throws Exception;
    
    /**
     * 获取全部有效的部门信息列表
     * @return
     */
    public List<Dept> getDeptmentAllList() throws Exception;
}