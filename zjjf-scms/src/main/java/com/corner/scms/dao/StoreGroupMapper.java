package com.corner.scms.dao;

import java.util.List;

import com.corner.core.beans.ScmsStore;
import com.corner.core.beans.SpVoucherActiveMiddle;
import com.corner.core.beans.StoreGroup;
import com.corner.scms.beans.ro.PromotionRo;
import com.corner.scms.beans.vo.StoreGroupVo;


public interface StoreGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(StoreGroup record);

    int insertSelective(StoreGroup record);

    StoreGroupVo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StoreGroup record);

    int updateByPrimaryKey(StoreGroup record);

	List<StoreGroup> getAllStoreGroupByCondition(StoreGroup condition);

	int getAllStoreGroupByConditionCount(StoreGroup condition);

	StoreGroup findXianXiaGroup(StoreGroup condition);

	List<ScmsStore> findNumber(StoreGroup condition);

	List<StoreGroup> findAllGroups(StoreGroup condition);

	void updateNumber(String storeGroupID);

	void updateNumberMis(String storeGroupID);

	Integer selectcountx(String id);

	List<StoreGroup> findGroupsByconditionalread(PromotionRo condition);

	List<StoreGroup> findGroupsByconditionno(PromotionRo condition);

	List<StoreGroup> findGroupsByconditionother(PromotionRo condition);

	List<StoreGroup> findGroupsByconditionalready(PromotionRo condition);

	List<StoreGroup> findGroupsByconditionalreadyno(PromotionRo v);

	SpVoucherActiveMiddle findSpVoucherActiveMiddle(StoreGroupVo condition);

	String findUrl();
	
	
}