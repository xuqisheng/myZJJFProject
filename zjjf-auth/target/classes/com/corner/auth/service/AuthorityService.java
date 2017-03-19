package com.corner.auth.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.corner.auth.beans.Admin;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.LoginRo;

public interface AuthorityService {


	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);

	Admin getUserByAdminCredential(String credential);

	ModelMsg dealAdminSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model);
}
