/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.tbldepartmentt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.service.CrudService;
import com.corner.salesman.commons.excel.ExcelUtils;
import com.corner.salesman.modules.tbldepartmentt.entity.TblDepartmentt;
import com.corner.salesman.modules.tbldepartmentt.dao.TblDepartmenttDao;

/**
 * 部门信息Service
 * @author 小金刚
 * @version 2016-08-16
 */
@Service
@Transactional(readOnly = true)
public class TblDepartmenttService extends CrudService<TblDepartmenttDao, TblDepartmentt> {
	@Autowired
	private TblDepartmenttDao tbldepartmenttdao;
	

	public TblDepartmentt get(String id) {
		return super.get(id);
	}
	
	public List<TblDepartmentt> findList(TblDepartmentt tblDepartmentt) {
		return super.findList(tblDepartmentt);
	}
	
	public Page<TblDepartmentt> findPage(Page<TblDepartmentt> page, TblDepartmentt tblDepartmentt) {
		return super.findPage(page, tblDepartmentt);
	}
	
	@Transactional(readOnly = false)
	public void save(TblDepartmentt tblDepartmentt) {
		super.save(tblDepartmentt);
	}
	
	@Transactional(readOnly = false)
	public void delete(TblDepartmentt tblDepartmentt) {
		super.delete(tblDepartmentt);
	}
	
	public List<TblDepartmentt> selpid(TblDepartmentt selpid){
		return tbldepartmenttdao.selpid(selpid);
		
	}
	
	public List<TblDepartmentt> userinfo(TblDepartmentt userinfo){
		return tbldepartmenttdao.userinfo(userinfo);
		
	}
	@Transactional(readOnly = false)
	public void deluser(TblDepartmentt deluser){
		tbldepartmenttdao.deluser(deluser);
	}
	
	public List<TblDepartmentt> getUserPageList(TblDepartmentt getuserpagelist){                                   
		return tbldepartmenttdao.getUserPageList(getuserpagelist);	
	}
	
	
}