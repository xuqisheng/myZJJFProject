package com.corner.scms.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.ScManager;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.beans.vo.base.MenuTreeNode;

public interface AuthorityService {

	ScManager getUserByScManagerCredential(String credential);
	
	Supplier getUserBySupplierCredential(String credential);
	
	CustomerService getUserByCustomerServiceCredential(String userName);

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);


	ModelMsg updateScMgPassword(String newPassword);
	ModelMsg updateSupplierPassword(String newPassword);
	ModelMsg updateKefuPassword(CustomerService cs);


	ModelMsg dealSupplierSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model);
	ModelMsg dealScMgSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model);
	ModelMsg dealKefuSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model);

	MenuTreeNode getSubjectMenus();
}
