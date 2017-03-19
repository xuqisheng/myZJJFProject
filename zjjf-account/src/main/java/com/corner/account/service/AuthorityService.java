package com.corner.account.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.corner.account.beans.ro.LoginRo;
import com.corner.account.beans.vo.MenuTreeNode;
import com.corner.core.beans.Accounter;
import com.corner.core.beans.msg.ModelMsg;

public interface AuthorityService {

	Accounter getUserByUserCredential(String credential);

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);

	ModelMsg dealSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model);

	ModelMsg updatePassword(String newPassword);

	MenuTreeNode getSubjectMenus();

}
