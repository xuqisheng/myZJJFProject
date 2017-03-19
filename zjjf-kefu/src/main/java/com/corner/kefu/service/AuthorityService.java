package com.corner.kefu.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.corner.core.beans.CustomerService;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.kefu.beans.ro.LoginRo;
import com.corner.kefu.beans.vo.MenuTreeNode;

public interface AuthorityService {


	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);


	ModelMsg updateKefuPassword(String newPassword);
	CustomerService getUserByCustomerServiceCredential(String credential);

	ModelMsg dealKefuSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model);

	MenuTreeNode getSubjectMenus();
}
