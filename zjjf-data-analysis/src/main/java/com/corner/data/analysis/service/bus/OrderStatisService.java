package com.corner.data.analysis.service.bus;

import java.util.List;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.OrderStatisVo;

/**
 * ClassName: OrderStatisService
 * 
 * @Description: 订单统计业务层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface OrderStatisService {
	/**
	 * @Description: 订单统计查询列表
	 * @param orderVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public List<OrderStatisVo> findOrderStatisList(OrderStatisVo orderVo) throws Exception;
	
	/**
	 * @Description: 订单统计分页查询方法
	 * @param orderVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public Pager<OrderStatisVo> findPagerList(OrderStatisVo orderVo) throws Exception;
}
