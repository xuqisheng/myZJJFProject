package com.corner.b2b.service;

import java.util.Set;

import com.corner.core.beans.Accounter;

public interface AuthorityService {

	Accounter getUserByUserCredential(String credential);

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);
//
//	ModelMsg dealSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model);
//
//	ModelMsg updatePassword(String newPassword);
//
//	MenuTreeNode getSubjectMenus();

}
