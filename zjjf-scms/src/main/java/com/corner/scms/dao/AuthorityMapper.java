package com.corner.scms.dao;

import java.util.Map;
import java.util.Set;

import com.corner.core.beans.*;
import com.corner.scms.beans.ro.auth.LoginRo;


public interface AuthorityMapper {

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);

	Set<Role> getSubjectRoleses(String id);

	Set<Auth> getSubjectAuths(String id);
//	***************************************************************
	String getSupplierUserIdByUserNameAndPassword(LoginRo loginRo);
//	***************************************************************
	/**根据手机号和类型获取用户条数	type 0-批发商	1-经销商	2-仓库	mobile	手机号**/
	Integer getUserByTypeAndMobile(Map<String, Object> map);

	Integer updateSupplierPasswordByDel(Map<String, Object> map);

	String getERPWarehouseUserUserIdByUserNameAndPassword(LoginRo loginRo);

	ERPWarehouseUser getUserByERPWarehouseUserCredential(String credential);

	ERPWarehouse getUserByERPWarehouseCredential(String credential);

	Supplier getSupplierByERPWarehouseUserId(String whId);
}