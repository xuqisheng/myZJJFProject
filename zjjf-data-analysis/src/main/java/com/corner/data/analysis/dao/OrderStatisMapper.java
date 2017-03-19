package com.corner.data.analysis.dao;

import java.util.List;

import com.corner.data.analysis.beans.vo.OrderStatisVo;
/**
 * ClassName: OrderStatisMapper
 * 
 * @Description: 订单统计 模型层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface OrderStatisMapper {

	/**
	 * @Description: 订单统计查询列表方法
	 * @param statisVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<OrderStatisVo> findOrderStatisList(OrderStatisVo orderVo);
	
	/**
	 * @Title: getPageListSize 
	 * @Description: 订单统计查询总数方法
	 * @param orderVo
	 * @return int   返回类型
	 * @author 元宝	yuanbao@izjjf.cn
     */
	public int getOrderStatisListSize(OrderStatisVo orderVo);
	
}
