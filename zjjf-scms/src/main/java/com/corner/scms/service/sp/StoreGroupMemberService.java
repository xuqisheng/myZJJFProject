package com.corner.scms.service.sp;

import com.corner.core.beans.StoreGroupMember;
import com.corner.scms.service.BaseService;

public interface StoreGroupMemberService extends BaseService{

	StoreGroupMember findMember(StoreGroupMember condition);

	void insertObject(StoreGroupMember condition);

	void deleteObject(String id);

	void updateObject(StoreGroupMember mem);

	void deleteByGroupId(String id);

}
