package com.corner.scms.dao;

import java.util.Set;

import com.corner.core.beans.Auth;
import com.corner.core.beans.CustomerService;
import com.corner.core.beans.Role;
import com.corner.core.beans.ScManager;
import com.corner.core.beans.Supplier;


public interface AuthorityMapper {

	ScManager getUserByScManagerCredential(String credential);
	
	Supplier getUserBySupplierCredential(String credential);
	
	CustomerService getUserByCustomerServiceCredential(String userName);

	Set<String> getRolesByUserId(String userId);

	Set<String> getAuthsByUserId(String userId);

	Set<Role> getSubjectRoleses(String id);

	Set<Auth> getSubjectAuths(String id);


}