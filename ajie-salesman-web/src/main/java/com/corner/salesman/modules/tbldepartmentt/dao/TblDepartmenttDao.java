/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.tbldepartmentt.dao;

import java.util.List;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.modules.tbldepartmentt.entity.TblDepartmentt;

/**
 * 部门信息DAO接口
 * @author 小金刚
 * @version 2016-08-16
 */
@MyBatisDao
public interface TblDepartmenttDao extends CrudDao<TblDepartmentt> {
	

	//查询部门领导
	public List<TblDepartmentt> selpid(TblDepartmentt selpid);
	
	//查询部门信息
	public List<TblDepartmentt>  userinfo(TblDepartmentt userinfo);
	
	//删除用户信息
	public void deluser(TblDepartmentt deluser);
	
	//根据部门ID查询所属的领导
	public List<TblDepartmentt> getUserPageList(TblDepartmentt getuserpagelist);
	
	//新增部门领导
	public void addlead(TblDepartmentt addlead);
}