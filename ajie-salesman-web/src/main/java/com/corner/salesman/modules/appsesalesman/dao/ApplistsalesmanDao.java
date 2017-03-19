/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.appsesalesman.dao;

import java.util.List;


import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.modules.appsesalesman.entity.Applistsalesman;

/**
 * 账户信息DAO接口
 * @author 小金刚
 * @version 2016-08-11
 */
@MyBatisDao
public interface ApplistsalesmanDao extends CrudDao<Applistsalesman> {
	
	public int addSalesman(Applistsalesman salesmanVO);
	
	public List<Applistsalesman> seldeptName(Applistsalesman seldeptName);

	public List<Applistsalesman> selpost(Applistsalesman label);

	
	public void addDeptName(Applistsalesman adddeptName);
	
	/**
	 * 查询部门名称
	 * @return
	 */
	public List<Applistsalesman> seldept();
	
	
}