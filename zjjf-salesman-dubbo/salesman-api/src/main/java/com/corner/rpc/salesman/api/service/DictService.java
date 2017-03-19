package com.corner.rpc.salesman.api.service;

import java.util.List;
import java.util.Map;

public interface DictService {
	/**
	 * 根据数据字典中的类型，获取对应数据列表
	 * @param type
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,String>> getDictListByType(String type) throws Exception;
    
    /**
     * 获取字典配置过滤部门编码列表信息（供过滤签到非业务员部门使用）
     * @return
     */
    public List<String> getFilterDeptList() throws Exception;
}
