package com.corner.rpc.salesman.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.salesman.api.service.DictService;
import com.corner.rpc.salesman.dao.DictMapper;

@Service("dictService")
public class DictServiceImpl implements DictService {

	@Autowired
	private DictMapper dictMapper;
	
	@Override
	public List<Map<String, String>> getDictListByType(String type) throws Exception {
		return dictMapper.getDictListByType(type);
	}

    /**
     * 获取字典配置过滤部门编码列表信息（供过滤签到非业务员部门使用）
     * @return
     */
    public List<String> getFilterDeptList() throws Exception{
    	return dictMapper.getFilterDeptList();
    }
}
