package com.corner.data.analysis.dao;

import java.util.List;

import com.corner.data.analysis.beans.vo.OrderListVo;
/**
 * ClassName: OrderMapper
 * 
 * @Description: 订单明细 模型层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface OrderListMapper {
	

	/**
	 * @Description: 订单明细查询列表方法
	 * @param orderVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<OrderListVo> getOrderList(OrderListVo orderVo);
	
	/**
	 * @Title: getPageListSize 
	 * @Description: 订单明细查询总数方法
	 * @param orderVo
	 * @return int   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public int getOrderListSize(OrderListVo orderVo);
	
}
