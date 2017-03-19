package com.corner.scms.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.Store;
import com.corner.core.beans.StoreGroup;
import com.corner.core.dao.StoreMapper;
import com.corner.scms.dao.StoreMgMapper;
import com.corner.scms.service.impl.BaseServiceImpl;
import com.corner.scms.service.sp.StoreService;


/**
 * 商铺Service
 * 
 * @author Howe at 2015年2月5日下午1:32:21
 * @Email itzihao@sina.com
 * @Desc
 */
@Service
public class StoreServiceImpl extends BaseServiceImpl implements StoreService{
	
	@Autowired
	private StoreMgMapper storeMgMapper;
	@Autowired
	StoreMapper storeMapper;

	@Override
	public List<Store> findAllNoGroupStore(StoreGroup condition) {
		return this.storeMgMapper.findAllNoGroupStore(condition);
	}

	@Override
	public boolean addAddressRemark(Integer storeId, String addressRemark) {
		Store store = new Store();
		store.setId(storeId);
		store.setAddressRemark(addressRemark);
		int num = storeMapper.updateByPrimaryKeySelective(store);
		if(num > 0){
			return true;
		}
		return false;
	}
	
	
}
