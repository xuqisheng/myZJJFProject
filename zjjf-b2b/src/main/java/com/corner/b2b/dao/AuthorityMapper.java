package com.corner.b2b.dao;

import java.util.Set;

import com.corner.core.beans.Accounter;
import com.corner.core.beans.Auth;
import com.corner.core.beans.Role;


public interface AuthorityMapper {

	Accounter getUserByUserCredential(String credential);

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);

	Set<Role> getSubjectRoleses(String id);

	Set<Auth> getSubjectAuths(String id);

}