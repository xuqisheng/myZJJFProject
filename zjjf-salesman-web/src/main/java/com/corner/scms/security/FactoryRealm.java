package com.corner.scms.security;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.corner.scms.beans.CustomerService;
import com.corner.scms.service.AuthorityService;

public class FactoryRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(FactoryRealm.class);

	@Autowired
	private AuthorityService authorityService;

	// 通过用户名获取真实的用户信息
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("================厂商登录认证：登录认证：FactoryRealm====================");
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
//				user = authorityService.getUserByScManagerCredential(credential);
				user = authorityService.getUserByCustomerServiceCredential(credential);
				/*user = new ScManager();
				user.setId("1ABE24E0DEA54A74A1C86F7D242428A3");
				user.setSupplierName("罗湖测试");
				user.setPassword("96e79218965eb72c92a549dd5a330112");
				user.setMobile("15255108902");*/
				logger.info("根据手机号获取当前用户：id：{},密码：{}，手机号：{}，用户名：{}", user.getId(), user.getPassword(), user.getMobile(), user.getUserName());
			} catch (Exception e) {
				return null;
			}finally{
				//if(user == null) throw new UnknownAccountException();				
			}

			SimpleAuthenticationInfo sauth = new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
			return sauth;
		}
	}

	// 获取授权信息
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.debug("================厂商登录认证：权限获取：FactoryRealm====================");
		String userId = (String) principals.fromRealm(getName()).iterator().next();
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
