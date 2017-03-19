package com.corner.scms.service.impl;

import com.corner.core.beans.*;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.dao.SupplierMapper;
import com.corner.core.utils.StringUtil;
import com.corner.core.utils.safe.MD5;
import com.corner.scms.beans.ro.auth.LoginRo;
import com.corner.scms.beans.vo.base.MenuTreeNode;
import com.corner.scms.beans.vo.base.UserInSession;
import com.corner.scms.config.SessionConfig;
import com.corner.scms.dao.AuthorityMapper;
import com.corner.scms.service.AuthorityService;
import com.corner.scms.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	private static Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);
	@Autowired
	AuthorityMapper authorityMapper;
	@Autowired
	SupplierMapper supplierMapper;
	@Autowired
	UserService userService;

	@Override
	public Supplier getUserBySupplierCredential(String credential) {
		return supplierMapper.selectByPrimaryKey(credential);
	}

	@Override
	public User getUserByCredential(String credential) {
		return userService.selectOne(credential);
	}

	@Override
	public ERPWarehouseUser getUserByERPWarehouseUserCredential(String credential) {
		return authorityMapper.getUserByERPWarehouseUserCredential(credential);
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
	 * 通过传入的类型查出用户的Id
	* @Title
	* @Description: TODO 
	* @param @param t
	* @param @param loginRo
	* @param @return
	* @2016年3月4日     
	* @author 龙五  longwu@izjjf.cn
	* @return
	* @throws
	 */
	public String getUserIdByLoginRo(String str,LoginRo loginRo) throws Exception{
		String userId = "";
		if(str.equals("supplier")){
			userId = authorityMapper.getSupplierUserIdByUserNameAndPassword(loginRo);
		}else if(str.equals("ERPWarehouseUser")){
			userId = authorityMapper.getERPWarehouseUserUserIdByUserNameAndPassword(loginRo);
		}else if(str.equals("user")){
			User user = userService.selectUserByLoginRo(loginRo);
			if(user == null){
				throw new Exception("用户名或密码不正确");
			}
			userId = user.getId();
		}
		if(StringUtil.stringIsNullOrEmpty(userId))
			throw new Exception("用户名或密码不正确");
		return  userId;
	}
	

	/**
	 * 批发商登录成功后的处理
	 */
	@Override
	public ModelMsg dealSupplierSuccessLogin(LoginRo loginRo, HttpServletRequest request, Model model , String userType) {
		Supplier rightUser = null;
		User user = null;
		if("ERPWarehouseUser".equals(userType)){
			ERPWarehouseUser erpWarehouseUser = authorityMapper.getUserByERPWarehouseUserCredential(loginRo.getUserName());
			SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.ERP_WAREHOUSE_USER_SESSION_KEY, erpWarehouseUser);
			ERPWarehouse erpWarehouse = authorityMapper.getUserByERPWarehouseCredential(loginRo.getUserName());
			SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.ERP_WAREHOUSE_SESSION_KEY, erpWarehouse);

			rightUser = authorityMapper.getSupplierByERPWarehouseUserId(erpWarehouseUser.getId());
		}else {
			user = userService.selectOne(loginRo.getUserName());
			rightUser = supplierMapper.selectByPrimaryKey(user.getSupplierId());
		}
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_SESSION_KEY, user);
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.SUPPLY_SESSION_KEY, rightUser);
		SecurityUtils.getSubject().getSession().setAttribute(SessionConfig.USER_TYPE_KEY, SessionConfig.USER_SUPPLIER);
		Supplier supplier = new Supplier();
		supplier.setId(rightUser.getId());
		supplier.setLoginIP(request.getRemoteAddr());
		supplier.setLastTime(new Date());
		int count = supplierMapper.updateByPrimaryKeySelective(supplier);
		if (count == 1) {
			logger.info("用户登录成功,id:{},手机号：{},名称,{}", rightUser.getId(), rightUser.getMobile(), rightUser.getSupplierName());
			return new ModelMsg(true, "登录成功");
		} else {
			return new ModelMsg(false, "数据异常");
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
	public MenuTreeNode getSubjectMenus() {
		Session session = SecurityUtils.getSubject().getSession();
		Accounter currentsubject = (Accounter) session.getAttribute(SessionConfig.USER_SESSION_KEY);
		if (currentsubject == null)
			return null;
		UserInSession userInSession = new UserInSession(currentsubject, authorityMapper);
		session.setAttribute(SessionConfig.USER_AUTHENTICATION, userInSession);
		return userInSession == null ? null : userInSession.getSubjectMenu();
	}

	/**
	 * type：
	 * 	 0-批发商	1-经销商	2-仓库	
	 * mobile	手机号 
	 */
	@Override
	public int getUserByTypeAndMobile(Map<String, Object> map) {
		return authorityMapper.getUserByTypeAndMobile(map);
	}

	@Override
	public int updateLoginPasswordByDel(Integer type, String phonenumber, String passwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phonenumber", phonenumber);
		map.put("passwd", passwd);
		Integer num = 0;
		if(type != null && type==1){
			num = authorityMapper.updateSupplierPasswordByDel(map);
		}
		
		return num;
	}

}
