package com.corner.rpc.salesman.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.rpc.salesman.api.service.SpGroupLineService;
import com.corner.rpc.salesman.dao.SpGroupLineMapper;
import com.corner.rpc.salesman.model.SpGroupLine;

@Service("spGroupLineService")
public class SpGroupLineServiceImpl implements SpGroupLineService {
	
	@Autowired
	private SpGroupLineMapper spgLineMapper;

	/**
	 * 根据业务员ID获取对应管理路线
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, String>> getLineHashMap(String userId) throws Exception {
		return spgLineMapper.getLineHashMap(userId);
	}

	/**
	 * 根据业务员ID获取对应管理定格列表
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
    public List<SpGroupLine> getMySpGroupList(String userId) throws Exception{
		
		List<SpGroupLine> list = spgLineMapper.getMySpGroupList(userId);
		if(null != list && !list.isEmpty()){
			for(SpGroupLine spgVO : list){
				
				if(null == spgVO){
					continue;
				}
				
				String spGroupId = spgVO.getSpGroupId();
				if("ALL".equals(spGroupId)){
					List<Map<String, String>> lineList = new ArrayList<Map<String, String>>();
					Map<String, String> map = new HashMap<String, String>();
					map.put("lineId", "ALL");
					map.put("lineName", "全部线路");
					lineList.add(map);
					spgVO.setLineList(lineList);
				}else{
					List<Map<String, String>> lineList = spgLineMapper.getSpgLineListById(spGroupId);
					spgVO.setLineList(lineList);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 根据区域ID查询区域定格列表
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	@Override
    public List<Map<String,String>> getSpGroupByAreaList(String areaId) throws Exception{
    	return spgLineMapper.getSpGroupByAreaList(areaId);
    }
    
	/**
	 * 根据定格ID获取线路列表
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
    @Override
    public List<Map<String,String>> getLineBySpGroupList(String spGroupId) throws Exception{
    	return spgLineMapper.getSpgLineListById(spGroupId);
    }
	
	/**
	 * 根据线路ID获取对应线路客户列表
	 * @param record
	 * @return
	 * @throws Exception
	 */
    @Override
    public List<Map<String,String>> getCustHashMap(SpGroupLine record) throws Exception{
    	return spgLineMapper.getCustHashMap(record);
    }
    
    
	/**
	 * 根据条件获取业务员管理商铺的坐标列表
	 * @param record
	 * @return
	 * @throws Exception
	 */
    @Override
    public List<Map<String,String>> getShopSiteList(SpGroupLine record) throws Exception{
    	return spgLineMapper.getShopSiteList(record);
    }
    
    /**
     * 获取定格管理的业务代表
     * @param userId
     * @return
     */
    @Override
    public String getSpGroupDbUserSet(String userId) throws Exception{
    	return spgLineMapper.getSpGroupDbUserSet(userId);
    }
    
    /**
     * 获取业务员所属定格ID
     * @param userId
     * @return
     */
    @Override
    public String getSpGroupByUserId(String userId) throws Exception{
    	return spgLineMapper.getSpGroupByUserId(userId);
    }
    
    /**
     * 查询用户是否是DB代表（大于0表示一定为db代表）
     * @param userId
     * @return
     */
    @Override
    public int queryUserIsDBType(String userId) throws Exception{
    	return spgLineMapper.queryUserIsDBType(userId);
    }
    
    
    /**
     * 获取部门及下属部门所有绑定的定格
     * @param paramMap
     * @return
     */
    public List<String> getDeptBindSpGroupList(Map<String,Object> paramMap) throws Exception{
    	return spgLineMapper.getDeptBindSpGroupList(paramMap);
    }
}
