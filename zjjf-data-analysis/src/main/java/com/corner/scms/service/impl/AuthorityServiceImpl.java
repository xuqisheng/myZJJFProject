package com.corner.scms.service.impl;

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
import com.corner.core.beans.ScManager;
import com.corner.core.beans.Supplier;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.CustomerServiceMapper;
import com.corner.core.dao.ScManagerMapper;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.utils.safe.MD5;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.beans.vo.base.MenuTreeNode;
import com.corner.scms.beans.vo.base.UserInSession;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.dao.AuthorityMapper;
import com.corner.scms.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	private static Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);

	@Autowired
	AuthorityMapper authorityMapper;

	@Autowired
	SupplierMapper supplierMapper;

	@Autowired
	CustomerServiceMapper csMapper;
	
	@Autowired
	ScManagerMapper scManagerMapper;

	@Override
	public ScManager getUserByScManagerCredential(String credential) {
		return authorityMapper.getUserByScManagerCredential(credential);
	}

	@Override
	public Supplier getUserBySupplierCredential(String credential) {
		return authorityMapper.getUserBySupplierCredential(credential);
	}
	
	@Override
	public CustomerService getUserByCustomerServiceCredential(String userName) {
		return authorityMapper.getUserByCustomerServiceCredential(userName);
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
	 * 品牌商登录成功后的处理
	 */
	@Override
	public ModelMsg dealScMgSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model) {
		ScManager rightUser2 = authorityMapper.getUserByScManagerCredential(loginRo.getUserName());
		if (rightUser2 == null) {
			return new ModelMsg(false, "未找到用户");
		} else {
			SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_SESSION_KEY, rightUser2);
			SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_TYPE_KEY, SessionConfig.USER_SCMG);
			ScManager scManager = new ScManager();
			scManager.setId(rightUser2.getId());
			scManager.setLoginIP(request.getRemoteAddr());
			scManager.setLastTime(new Date());
			int count = scManagerMapper.updateByPrimaryKeySelective(scManager);
			if (count == 1) {
				logger.info("用户登陆成功,id:{},手机号：{},名称,{}", rightUser2.getId(), rightUser2.getMobile(), rightUser2.getSupplierName());
				return new ModelMsg(true, "登陆成功");
			} else {
				return new ModelMsg(false, "数据异常");
			}
		}
	}

	/**
	 * 批发商登录成功后的处理
	 */
	@Override
	public ModelMsg dealSupplierSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model) {
		//Supplier rightUser = authorityMapper.getUserBySupplierCredential(loginRo.getUserName());
		CustomerService rightUser = authorityMapper.getUserByCustomerServiceCredential(loginRo.getUserName());
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_SESSION_KEY, rightUser);
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_TYPE_KEY, SessionConfig.USER_SUPPLIER);
		Supplier supplier = new Supplier();
		supplier.setId(rightUser.getId());
		supplier.setLoginIP(request.getRemoteAddr());
		supplier.setLastTime(new Date());
		int count = supplierMapper.updateByPrimaryKeySelective(supplier);
		if (count == 1) {
			logger.info("用户登陆成功,id:{},手机号：{},名称,{}", rightUser.getId(), rightUser.getMobile(), rightUser.getUserName());
			return new ModelMsg(true, "登陆成功");
		} else {
			return new ModelMsg(false, "数据异常");
		}
	}
	
	/**
	 * 客服登录成功后的处理
	 */
	@Override
	public ModelMsg dealKefuSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model) {
		CustomerService rightUser = authorityMapper.getUserByCustomerServiceCredential(loginRo.getUserName());
		if(null == rightUser){
			return new ModelMsg(false, "用户信息不存在！");
		}
		
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_SESSION_KEY, rightUser);
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_TYPE_KEY, SessionConfig.USER_KEFU);
		CustomerService cs = new CustomerService();
		cs.setId(rightUser.getId());
		cs.setLastIP(request.getRemoteAddr());
		cs.setLastTime(new Date());
		int count = csMapper.updateByPrimaryKeySelective(cs);
		if (count == 1) {
			logger.info("用户登陆成功,id:{},手机号：{},名称,{}", rightUser.getId(), rightUser.getMobile(), rightUser.getUserName());
			return new ModelMsg(true, "登陆成功");
		} else {
			return new ModelMsg(false, "数据异常");
		}
	}
		
	
	@Override
	public ModelMsg updateScMgPassword(String newPassword) {
		ScManager scManager = new ScManager();
		ScManager current = (ScManager) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		scManager.setId(current.getId());
		scManager.setPassword(MD5.StringToMd5(newPassword));
		int count = scManagerMapper.updateByPrimaryKeySelective(scManager);
		if (count == 1) {
			return new ModelMsg(true, newPassword);
		} else {
			return new ModelMsg(false, "密码修改异常");
		}
	}
	
	@Override
	public ModelMsg updateSupplierPassword(String newPassword) {
		Supplier supplier = new Supplier();
		Supplier current = (Supplier) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		supplier.setId(current.getId());
		supplier.setPassword(MD5.StringToMd5(newPassword));
		int count = supplierMapper.updateByPrimaryKeySelective(supplier);
		if (count == 1) {
			return new ModelMsg(true, newPassword);
		} else {
			return new ModelMsg(false, "密码修改异常");
		}
	}
	
	@Override
	public ModelMsg updateKefuPassword(CustomerService cs) {
//		CustomerService cs = new CustomerService();
		CustomerService current = (CustomerService) SecurityUtils.getSubject().getSession().getAttribute(SessionConfig.USER_SESSION_KEY);
		cs.setId(current.getId());
		cs.setPassword(MD5.StringToMd5(cs.getPassword()));
		int count = csMapper.updateByPrimaryKeySelective(cs);
		if (count == 1) {
			return new ModelMsg(true, "密码修改成功");
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
