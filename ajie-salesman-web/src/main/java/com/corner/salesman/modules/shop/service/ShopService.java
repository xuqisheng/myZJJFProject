/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.shop.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.corner.salesman.common.persistence.Page;
import com.corner.salesman.common.service.CrudService;
import com.corner.salesman.modules.shop.entity.Shop;
import com.corner.salesman.modules.shop.dao.ShopDao;

/**
 * 客户管理Service
 * @author setsail
 * @version 2016-08-05
 */
@Service
@Transactional(readOnly = true)
public class ShopService extends CrudService<ShopDao, Shop> {

	public Shop get(String id) {
		return super.get(id);
	}
	
	public List<Shop> findList(Shop shop) {
		return super.findList(shop);
	}
	
	public Page<Shop> findPage(Page<Shop> page, Shop shop) {
		
		StringBuffer sbShop = new StringBuffer();
		String shopIds = shop.getShopIds();
		//店铺集合的ID不为空，则按照规则拼接成in的查询条件带到sql中
		if(StringUtils.isNotBlank(shopIds)&& !shopIds.contains("'")){
			String[] shopIdSet = shopIds.split(",");
			for (int i = 0; i < shopIdSet.length; i++) {
				if(i<shopIdSet.length-1){
					sbShop.append("'").append(shopIdSet[i]).append("'").append(",");
				}else{
					sbShop.append("'").append(shopIdSet[i]).append("'");
				}
			}
			
			shop.setShopIds(sbShop.toString());
		}
		
		return super.findPage(page, shop);
	}
	
	@Transactional(readOnly = false)
	public void save(Shop shop) {
		super.save(shop);
	}
	
	@Transactional(readOnly = false)
	public void delete(Shop shop) {
		super.delete(shop);
	}
	
}