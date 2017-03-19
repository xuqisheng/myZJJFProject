package com.corner.auth.service.impl;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.corner.auth.beans.Admin;
import com.corner.auth.beans.msg.ModelMsg;
import com.corner.auth.beans.ro.LoginRo;
import com.corner.auth.config.SessionConfig;
import com.corner.auth.dao.AdminMapper;
import com.corner.auth.dao.AuthorityMapper;
import com.corner.auth.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	private static Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

	@Autowired
	AuthorityMapper authorityMapper;
	@Autowired
	AdminMapper adminMapper;
	@Override
	public Admin getUserByAdminCredential(String credential) {
		return authorityMapper.getUserByAdminCredential(credential);
	}

	@Override
	public Set<String> getRolesByUserId(String userId) {
		return authorityMapper.getRolesByUserId(userId);
	}

	@Override
	public Set<String> getAuthsByUserId(String userId) {
		return authorityMapper.getAuthsByUserId(userId);
	}

	
	/**
	 * admin登录成功后的处理
	 */
	@Override
	public ModelMsg dealAdminSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model) {
		Admin admin = authorityMapper.getUserByAdminCredential(loginRo.getUserName());
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_SESSION_KEY, admin);
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_TYPE_KEY, SessionConfig.USER_ADMIN);
		
		Admin cs = new Admin();
		cs.setId(admin.getId());
		cs.setLastIP(request.getRemoteAddr());
		cs.setLastTime(new Date());
		int count = adminMapper.updateByPrimaryKeySelective(cs);
		if (count == 1) {
			logger.info("用户登录成功,id:{},手机号：{},名称,{}", admin.getId(), admin.getUserName());
			return new ModelMsg(true, "登录成功");
		} else {
			return new ModelMsg(false, "数据异常");
		}
	}
}
