/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.appsesalesman.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.service.CrudService;
import com.corner.salesman.commons.utils.UuidUtil;
import com.corner.salesman.modules.appsesalesman.dao.ApplistsalesmanDao;
import com.corner.salesman.modules.appsesalesman.entity.Applistsalesman;
import com.corner.salesman.modules.sys.entity.User;
import com.corner.salesman.modules.sys.utils.UserUtils;

/**
 * 账户信息Service
 * @author 小金刚
 * @version 2016-08-11
 */
@Service
@Transactional(readOnly = true)
public class ApplistsalesmanService extends CrudService<ApplistsalesmanDao, Applistsalesman> {

	@Autowired
	private ApplistsalesmanDao applistsalesmanDao;
	
	public Applistsalesman get(Applistsalesman applistsalesman,String id) {
		applistsalesmanDao.seldeptName(applistsalesman);
		applistsalesmanDao.selpost(applistsalesman);
		return super.get(id);
	}
	
	public List<Applistsalesman> findList(Applistsalesman applistsalesman) {
			
			return super.findList(applistsalesman);
	}
	
	public Page<Applistsalesman> findPage(Page<Applistsalesman> page, Applistsalesman applistsalesman) {
		return super.findPage(page, applistsalesman);
	}
	
	@Transactional(readOnly = false)
	public void salesmanAdd(Applistsalesman applistsalesman) {
		User user = UserUtils.getUser();
		applistsalesman.setCreateBy(user);
		
		super.save(applistsalesman);
	}
	
	@Transactional(readOnly = false)
	public void addSalesman(Applistsalesman applistsalesman) {
		applistsalesman.setId(UuidUtil.get32UUID());
		applistsalesmanDao.addSalesman(applistsalesman);
		applistsalesmanDao.addDeptName(applistsalesman);
	}
	
	@Transactional(readOnly = false)
	public void delete(Applistsalesman applistsalesman){
		super.delete(applistsalesman);
	}
	
	public List<Applistsalesman> seldeptName(Applistsalesman seldeptName) {
		return applistsalesmanDao.seldeptName(seldeptName);
	}
	
	
	public List<Applistsalesman> selpost(Applistsalesman label){
		return applistsalesmanDao.selpost(label);
		
	}
	
}