package com.corner.data.analysis.service.bus.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.OrderListVo;
import com.corner.data.analysis.dao.OrderListMapper;
import com.corner.data.analysis.service.bus.OrderListService;

/**
 * ClassName: StoreServiceImpl
 * 
 * @Description: 店铺信息业务逻辑层
 * @author 元宝
 * @date 2016年01月8日
 */
@Service
public class OrderListServiceImpl implements OrderListService {
	
	@Autowired
	private OrderListMapper orderListMapper;
	
	/**
	 * @Description: 订单明细分页查询方法
	 * @param orderVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public Pager<OrderListVo> findPagerList(OrderListVo orderVo) throws Exception{
		List<OrderListVo> list = orderListMapper.getOrderList(orderVo);
		int size = orderListMapper.getOrderListSize(orderVo);
		return new Pager<OrderListVo>(size,list);
	}

}
