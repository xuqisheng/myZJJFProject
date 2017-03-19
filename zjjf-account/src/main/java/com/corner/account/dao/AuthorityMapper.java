package com.corner.account.dao;

import com.corner.core.beans.Accounter;
import com.corner.core.beans.Auth;
import com.corner.core.beans.Role;

import java.util.Set;


public interface AuthorityMapper {

	Accounter getUserByUserCredential(String credential);

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);

	Set<Role> getSubjectRoleses(String id);

	Set<Auth> getSubjectAuths(String id);

}