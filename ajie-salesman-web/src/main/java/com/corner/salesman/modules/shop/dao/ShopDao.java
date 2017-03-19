/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.shop.dao;

import com.corner.salesman.common.persistence.CrudDao;
import com.corner.salesman.common.persistence.annotation.MyBatisDao;
import com.corner.salesman.modules.shop.entity.Shop;

/**
 * 客户管理DAO接口
 * @author setsail
 * @version 2016-08-05
 */
@MyBatisDao
public interface ShopDao extends CrudDao<Shop> {
	
}