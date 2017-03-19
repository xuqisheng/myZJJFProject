package com.corner.kefu.service.sp.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.kefu.beans.vo.sp.StoreVo;
import com.corner.kefu.dao.sp.TempStoreMgMapper;
import com.corner.kefu.service.sp.TempStoreService;


@Service
public class TempStoreServiceImpl implements TempStoreService {
	
	@Autowired
    TempStoreMgMapper tempStoreMgMapper;
	
	@Override
	public List<StoreVo> getStoreList(Map<String, Object> map) throws Exception {
		return tempStoreMgMapper.getStoreList(map);
	}

	@Override
	public void delTempStore(Map<String, Object> map) throws Exception {
       tempStoreMgMapper.delTempStore(map);		
	}

	/**
	 * 
	* Title: batchSave 
	* Description:往TempStroe表中插入数据 
	* @param map
	* @throws Exception 
	* @see com.corner.kefu.service.sp.TempStoreService#batchSave(java.util.Map)
	 */
	@Override
	public void batchSave(Map<String, Object> map) throws Exception {
		tempStoreMgMapper.batchSave(map);
	}

	
	/**
	 * 
	* Title: getTempStoreList 
	* Description:获取TempStore表中的数据 
	* @param map
	* @return
	* @throws Exception 
	* @see com.corner.kefu.service.sp.TempStoreService#getTempStoreList(java.util.Map)
	 */
	@Override
	public List<StoreVo> getTempStoreList(Map<String, Object> map) throws Exception {
		
		return tempStoreMgMapper.getTempStoreList(map);
	}

	@Override
	public Integer getCountTempStoreList(Map<String, Object> map) throws Exception {
		return tempStoreMgMapper.getCountTempStoreList(map);
	}

}
