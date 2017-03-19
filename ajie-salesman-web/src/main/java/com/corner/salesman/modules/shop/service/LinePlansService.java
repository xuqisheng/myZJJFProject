/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.service.CrudService;
import com.corner.salesman.modules.shop.entity.LinePlans;
import com.corner.salesman.modules.shop.dao.LinePlansDao;

/**
 * 线路规划Service
 * @author setsail
 * @version 2016-08-05
 */
@Service("lPlansService")
@Transactional(readOnly = true)
public class LinePlansService extends CrudService<LinePlansDao, LinePlans> {

	public LinePlans get(String id) {
		return super.get(id);
	}
	
	public List<LinePlans> findList(LinePlans linePlans) {
		return super.findList(linePlans);
	}
	
	public Page<LinePlans> findPage(Page<LinePlans> page, LinePlans linePlans) {
		return super.findPage(page, linePlans);
	}
	
	@Transactional(readOnly = false)
	public void save(LinePlans linePlans) {
		super.save(linePlans);
	}
	
	@Transactional(readOnly = false)
	public void delete(LinePlans linePlans) {
		super.delete(linePlans);
	}
	
}