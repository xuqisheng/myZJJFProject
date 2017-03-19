package com.corner.data.analysis.service.bus;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.OrderListVo;

/**
 * ClassName: OrderListService
 * 
 * @Description: 订单明细业务层接口
 * @author 元宝
 * @date 2016年01月8日
 */
public interface OrderListService {
	/**
	 * @Description: 订单明细分页查询方法
	 * @param orderVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	public Pager<OrderListVo> findPagerList(OrderListVo orderVo) throws Exception;
}
