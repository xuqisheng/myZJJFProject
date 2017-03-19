/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.apprunlog.dao;

import java.util.List;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.modules.apprunlog.entity.AppRunLog;

/**
 * App运行日志DAO接口
 * @author 小金刚
 * @version 2016-08-30
 */
@MyBatisDao
public interface AppRunLogDao extends CrudDao<AppRunLog> {
		/**
		 * App运行日志导出方法
		 * @param apprunlog
		 * @return
		 */
		public List<AppRunLog> findAppRunLogList(AppRunLog apprunlog);
}