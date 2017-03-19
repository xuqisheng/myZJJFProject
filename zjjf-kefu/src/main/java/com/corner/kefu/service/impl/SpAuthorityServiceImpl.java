package com.corner.kefu.service.impl;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.corner.core.beans.Accounter;
import com.corner.core.beans.CustomerService;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.CustomerServiceMapper;
import com.corner.core.dao.ScManagerMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.utils.safe.MD5;
import com.corner.kefu.beans.UserInSession;
import com.corner.kefu.beans.ro.LoginRo;
import com.corner.kefu.beans.vo.MenuTreeNode;
import com.corner.kefu.config.SessionConfig;
import com.corner.kefu.dao.AuthorityMapper;
import com.corner.kefu.service.AuthorityService;

@Service
public class SpAuthorityServiceImpl implements AuthorityService {

	private static Logger logger = LoggerFactory.getLogger(SpAuthorityServiceImpl.class);

	@Autowired
	AuthorityMapper authorityMapper;

	@Autowired
	SupplierMapper supplierMapper;

	@Autowired
	CustomerServiceMapper csMapper;
	
	@Autowired
	ScManagerMapper scManagerMapper;

	@Override
	public CustomerService getUserByCustomerServiceCredential(String credential) {
		return authorityMapper.getUserByCustomerServiceCredential(credential);
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
	 * 客服登录成功后的处理
	 */
	@Override
	public ModelMsg dealKefuSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model) {
		CustomerService rightUser = authorityMapper.getUserByCustomerServiceCredential(loginRo.getUserName());
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_SESSION_KEY, rightUser);
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_TYPE_KEY, SessionConfig.USER_KEFU);
		CustomerService cs = new CustomerService();
		cs.setId(rightUser.getId());
		cs.setLastIP(request.getRemoteAddr());
		cs.setLastTime(new Date());
		int count = csMapper.updateByPrimaryKeySelective(cs);
		if (count == 1) {
			logger.info("用户登录成功,id:{},手机号：{},名称,{}", rightUser.getId(), rightUser.getMobile(), rightUser.getUserName());
			return new ModelMsg(true, "登录成功");
		} else {
			return new ModelMsg(false, "数据异常");
		}
	}
		
	
	@Override
	public ModelMsg updateKefuPassword(String newPassword) {
		CustomerService cs = new CustomerService();
		CustomerService current = (CustomerService) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		cs.setId(current.getId());
		cs.setPassword(MD5.StringToMd5(newPassword));
		int count = csMapper.updateByPrimaryKeySelective(cs);
		if (count == 1) {
			return new ModelMsg(true, newPassword);
		} else {
			return new ModelMsg(false, "密码修改异常");
		}
	}

	@Override
	public MenuTreeNode getSubjectMenus() {
		Session session = SecurityUtils.getSubject().getSession();
		Accounter currentsubject = (Accounter) session.getAttribute(SessionConfig.USER_SESSION_KEY);
		if (currentsubject == null)
			return null;
		UserInSession userInSession = new UserInSession(currentsubject, authorityMapper);
		session.setAttribute(SessionConfig.USER_AUTHENTICATION, userInSession);
		return userInSession == null ? null : userInSession.getSubjectMenu();
	}

}
