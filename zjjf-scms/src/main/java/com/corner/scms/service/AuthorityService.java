package com.corner.scms.service;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.corner.core.beans.ERPWarehouseUser;
import com.corner.core.beans.User;
import org.springframework.ui.Model;

import com.corner.core.beans.ScManager;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.beans.vo.base.MenuTreeNode;

public interface AuthorityService {

	Supplier getUserBySupplierCredential(String credential);
	User getUserByCredential(String credential);
	ERPWarehouseUser getUserByERPWarehouseUserCredential(String credential);

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);


	ModelMsg updateSupplierPassword(String newPassword);

	ModelMsg dealSupplierSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model , String userType);

	MenuTreeNode getSubjectMenus();
	

	String getUserIdByLoginRo(String str,LoginRo loginRo) throws Exception;
	/** type：1-批发商	2-经销商	3-仓库	mobile	手机号**/
	int getUserByTypeAndMobile(Map<String, Object> map);
	
	int updateLoginPasswordByDel(Integer type,String phonenumber,String passwd);
}
