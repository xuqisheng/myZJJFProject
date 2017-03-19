/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.applog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.service.CrudService;
import com.corner.salesman.modules.applog.entity.Applog;
import com.corner.salesman.modules.applog.dao.ApplogDao;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;

/**
 * App日志监控Service
 * @author 小金刚
 * @version 2016-08-29
 */
@Service
@Transactional(readOnly = true)
public class ApplogService extends CrudService<ApplogDao, Applog> {
	@Autowired
	private ApplogDao dao;
	
	public void setDao(ApplogDao dao) {
		this.dao = dao;
	}

	public Applog get(String id) {
		return super.get(id);
	}
	
	public List<Applog> findList(Applog applog) {
		return super.findList(applog);
	}
	
	public Page<Applog> findPage(Page<Applog> page, Applog applog) {
		return super.findPage(page, applog);
	}
	
	@Transactional(readOnly = false)
	public void save(Applog applog) {
		super.save(applog);
	}
	
	@Transactional(readOnly = false)
	public void delete(Applog applog) {
		super.delete(applog);
	}
	
	@Transactional(readOnly = false)
	public List<Applog> findErrorLogList(Applog applog){
		return dao.findErrorLogList(applog);
		
	}
}