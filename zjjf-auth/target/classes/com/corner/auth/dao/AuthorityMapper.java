package com.corner.auth.dao;

import java.util.Set;

import com.corner.auth.beans.Admin;
import com.corner.auth.beans.Auth;
import com.corner.auth.beans.Role;


public interface AuthorityMapper {

	Admin getUserByAdminCredential(String userName);

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);

	Set<Role> getSubjectRoleses(String id);

	Set<Auth> getSubjectAuths(String id);


}