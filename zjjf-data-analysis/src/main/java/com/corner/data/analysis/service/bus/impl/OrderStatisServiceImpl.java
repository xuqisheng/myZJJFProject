package com.corner.data.analysis.service.bus.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.data.analysis.beans.vo.OrderStatisVo;
import com.corner.data.analysis.dao.OrderStatisMapper;
import com.corner.data.analysis.service.bus.OrderStatisService;

/**
 * ClassName: OrderStatisServiceImpl
 * 
 * @Description: 订单统计业务逻辑层
 * @author 元宝
 * @date 2016年01月8日
 */
@Service
public class OrderStatisServiceImpl implements OrderStatisService {
	
	@Autowired
	private OrderStatisMapper statisMapper;

	/**
	 * @Description: 订单统计查询方法
	 * @param orderVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public List<OrderStatisVo> findOrderStatisList(OrderStatisVo orderVo) throws Exception {
		return statisMapper.findOrderStatisList(orderVo);
	}
	
	/**
	 * @Description: 订单统计分页查询方法
	 * @param orderVo
	 * @return 
	 * @author 元宝	yuanbao@izjjf.cn
	 */
	@Override
	public Pager<OrderStatisVo> findPagerList(OrderStatisVo orderVo) throws Exception {
		List<OrderStatisVo> list = statisMapper.findOrderStatisList(orderVo);
		int size = statisMapper.getOrderStatisListSize(orderVo);
		return new Pager<OrderStatisVo>(size,list);
	}
}
