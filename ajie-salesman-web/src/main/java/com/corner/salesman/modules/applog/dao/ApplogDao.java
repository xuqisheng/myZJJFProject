/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.applog.dao;

import java.util.List;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.modules.applog.entity.Applog;

/**
 * App日志监控DAO接口
 * @author 小金刚
 * @version 2016-08-29
 */
@MyBatisDao
public interface ApplogDao extends CrudDao<Applog> {
		public List<Applog> findErrorLogList(Applog applog);
	
}