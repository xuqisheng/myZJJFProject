package com.corner.rpc.salesman.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.salesman.api.service.DeptService;
import com.corner.rpc.salesman.dao.DeptMapper;
import com.corner.rpc.salesman.model.Dept;

/**
 * 部门业务层
 * @author Administrator
 *
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public List<Map<String, Object>> getDeptLevelHashMap(Dept record) throws Exception {
		return deptMapper.getDeptLevelHashMap(record);
	}

    /**
     * 获取全部有效的部门信息列表
     * @return
     */
	@Override
    public List<Dept> getDeptmentAllList() throws Exception{
		return deptMapper.getDeptmentAllList();
    }
}
