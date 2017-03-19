/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.apprunlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.service.CrudService;
import com.corner.salesman.modules.apprunlog.entity.AppRunLog;
import com.corner.salesman.modules.apprunlog.dao.AppRunLogDao;

/**
 * App运行日志Service
 * @author 小金刚
 * @version 2016-08-30
 */
@Service
@Transactional(readOnly = true)
public class AppRunLogService extends CrudService<AppRunLogDao, AppRunLog> {
	@Autowired
	private AppRunLogDao dao;
	
	public void setDao(AppRunLogDao dao) {
		this.dao = dao;
	}

	public AppRunLog get(String id) {
		return super.get(id);
	}
	
	public List<AppRunLog> findList(AppRunLog appRunLog) {
		return super.findList(appRunLog);
	}
	
	public Page<AppRunLog> findPage(Page<AppRunLog> page, AppRunLog appRunLog) {
		return super.findPage(page, appRunLog);
	}
	
	@Transactional(readOnly = false)
	public void save(AppRunLog appRunLog) {
		super.save(appRunLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(AppRunLog appRunLog) {
		super.delete(appRunLog);
	}
	
	@Transactional(readOnly = false)
	public List<AppRunLog> findAppRunLogList(AppRunLog apprunlog){
		return dao.findAppRunLogList(apprunlog);
		
	}
	
}