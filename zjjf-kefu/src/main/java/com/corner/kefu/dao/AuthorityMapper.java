package com.corner.kefu.dao;

import java.util.Set;

import com.corner.core.beans.Auth;
import com.corner.core.beans.CustomerService;
import com.corner.core.beans.Role;


public interface AuthorityMapper {

	CustomerService getUserByCustomerServiceCredential(String userName);

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);

	Set<Role> getSubjectRoleses(String id);

	Set<Auth> getSubjectAuths(String id);


}