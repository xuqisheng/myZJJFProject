package com.corner.data.analysis.security;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.corner.core.beans.CustomerService;
import com.corner.scms.service.AuthorityService;

public class SupplierRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(SupplierRealm.class);

	@Autowired
	private AuthorityService authorityService;

	// 通过用户名获取真实的用户信息
	/*@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("================doGetAuthenticationInfo====================");
		if (authcToken == null) {
			return null;
		} else {
			UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
			String credential = token.getUsername();
			if (StringUtils.isEmpty(credential)) {
				return null;
			}
			Supplier user = null;
			try {
				user = authorityService.getUserBySupplierCredential(credential);
				logger.info("根据手机号获取当前用户：id：{},密码：{}，手机号：{}，用户名：{}", user.getId(), user.getPassword(), user.getMobile(), user.getSupplierName());
			} catch (Exception e) {
				return null;
			}finally{
				if(user == null) throw new UnknownAccountException();				
			}
			SimpleAuthenticationInfo sauth = new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
			return sauth;
		}
	}*/
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("================doGetAuthenticationInfo====================");
		if (authcToken == null) {
			return null;
		} else {
			UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
			String credential = token.getUsername();
			if (StringUtils.isEmpty(credential)) {
				return null;
			}
			CustomerService user = null;
			try {
				user = authorityService.getUserByCustomerServiceCredential(credential);
				logger.info("根据手机号获取当前用户：id：{},密码：{}，手机号：{}，用户名：{}", user.getId(), user.getPassword(), user.getMobile(), user.getUserName());
			} catch (Exception e) {
				return null;
			}finally{
				if(user == null) throw new UnknownAccountException();				
			}
			SimpleAuthenticationInfo sauth = new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
			return sauth;
		}
	}

	// 获取授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.debug("================doGetAuthorizationInfo====================");
		String userId = (String) principals.fromRealm(getName()).iterator().next();
		logger.debug("============当前用户:" + userId + "================");
		if (!StringUtils.isEmpty(userId)) {
			SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
			Set<String> rolesSet = authorityService.getRolesByUserId(userId);
			authorizationInfo.setRoles(rolesSet);
			Set<String> auths = authorityService.getAuthsByUserId(userId);
			authorizationInfo.setStringPermissions(auths);
			return authorizationInfo;
		}
		return null;
	}

}
