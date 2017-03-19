package com.corner.kefu.service.sp;

import java.util.List;

/**
 * 购物车service
 * 
 * @author aimee at 2015年2月3日下午5:31:22
 * @email 1297579898@qq.com
 */
public interface SpShoppingCartService{
	/**
	 * 清除购物车
	 * 
	 * @author luke at  2015年10月27日上午11:13:27
	 * @email tiezhongtang@izjjf.cn
	 * @param int
	 * @return
	 */
	int emptySpShopCartByUserId(List<String> userIdList);
}
