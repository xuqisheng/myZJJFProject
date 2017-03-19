package com.corner.kefu.dao.sp;

import java.util.List;

/**
 * 开店宝购物车dao
 * 
 * @author aimee at 2015年2月3日下午3:31:04
 * @email 1297579898@qq.com
 */
public interface SpShoppingCartMgMapper{
	/**
	 * 清除购物车，用于购物车同步
	 * 
	 * @author luke at  2015年10月27日上午11:13:27
	 * @email tiezhongtang@izjjf.cn
	 * @param int
	 * @return
	 */
	public int emptySpShopCartByUserId(List<String> userIdList);	
}
