package com.corner.kefu.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.kefu.dao.sp.SpShoppingCartMgMapper;
import com.corner.kefu.service.sp.SpShoppingCartService;

/**
 * 购物车service
 * 
 * @author aimee at 2015年2月3日下午5:31:22
 * @email 1297579898@qq.com
 */
@Service
public class SpShoppingCartServiceImpl implements SpShoppingCartService{
	@Autowired
	SpShoppingCartMgMapper spShoppingCartMgMapper;
	/**
	 * 清除购物车
	 * 
	 * @author luke at  2015年10月27日上午11:13:27
	 * @email tiezhongtang@izjjf.cn
	 * @param int
	 * @return
	 */
	@Override
	public int emptySpShopCartByUserId(List<String> userIdList) {
		return spShoppingCartMgMapper.emptySpShopCartByUserId(userIdList);
	}
}
