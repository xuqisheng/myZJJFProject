package com.corner.scms.service.sp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corner.core.beans.Store;
import com.corner.core.beans.StoreGroup;
import com.corner.scms.service.BaseService;


/**
 * 商铺Service
 * 
 * @author Howe at 2015年2月5日下午1:32:21
 * @Email itzihao@sina.com
 * @Desc
 */
@Service
public interface StoreService extends BaseService{

	List<Store> findAllNoGroupStore(StoreGroup condition);

	boolean addAddressRemark(Integer storeId, String addressRemark);
	
	
	
	
}
