package com.corner.kefu.service.sp.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.StoreInfo;
import com.corner.core.dao.StoreInfoMapper;
import com.corner.kefu.service.sp.SpStoreInfoService;

@Service
public class SpStoreInfoServiceImpl implements SpStoreInfoService{
	@Autowired
	StoreInfoMapper storeInfoMapper;
	/**
	 * 保存店铺拓展信息
	 * @param storeInfo
	 * @return
	 */
	@Override
	public int save(StoreInfo storeInfo) {
		return storeInfoMapper.insertSelective(storeInfo);
	}

	/**
	 * 更新店铺拓展信息
	 * @param storeInfo
	 * @return
	 */
	@Override
	public int updateStore(StoreInfo storeInfo) {
		return storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
	}
	
	/**
	 * 根据id查询StoreInfo的内容
	 * @param id
	 * @return
	 */
	@Override
	public StoreInfo getStoreById(Integer id) {
		return storeInfoMapper.selectByPrimaryKey(id);
	}
	
}
