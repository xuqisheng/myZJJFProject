package com.corner.rpc.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.shop.api.service.SpGroupService;
import com.corner.rpc.shop.dao.SpGroupMapper;
import com.corner.rpc.shop.model.SpGroup;
/**
 * 店宝定格业务实现类
 * 
 * @author yuanbao
 */
@Service("spGroupService")
public class SpGroupServiceImpl implements SpGroupService {

	@Autowired
	private SpGroupMapper spgMapper;
	
	/**
	 * 根据区域ID查询定格列表信息
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SpGroup> querySpGroupListByAreaId(Integer areaId) throws Exception {
		
		List<SpGroup> list = spgMapper.querySpGroupListByAreaId(areaId);
		return list;
	}
	
	/**
	 * 根据查询条件获取对应区域定格列表
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public List<SpGroup> querySpGroupList(SpGroup record) throws Exception{
		return spgMapper.querySpGroupList(record);
	}
	
	/**
	 * 获取全部有效的定格信息（供excel导入的时候使用）
	 */
	@Override
	public List<HashMap<String, String>> getEnableSpGroupCache() throws Exception {
		return spgMapper.getEnableSpGroupCache();
	}

}
