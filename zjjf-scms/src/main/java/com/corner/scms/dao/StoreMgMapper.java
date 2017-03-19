package com.corner.scms.dao;

import java.util.List;

import com.corner.core.beans.Store;
import com.corner.core.beans.StoreGroup;
import com.corner.core.beans.StoreGroupMember;


public interface StoreMgMapper {

	List<Store> findAllNoGroupStore(StoreGroup condition);

	List<Store> findStores(String id);

	
	StoreGroupMember findMember(StoreGroupMember condition);

	void deleteByGroupId(String id);

	int selectcountx(String id);
	
	
}