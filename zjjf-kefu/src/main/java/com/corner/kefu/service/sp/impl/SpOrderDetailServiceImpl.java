package com.corner.kefu.service.sp.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpOrderDetail;
import com.corner.kefu.beans.vo.sp.DgSpOrderDetail;
import com.corner.kefu.dao.sp.SpOrderDetailMgMapper;
import com.corner.kefu.service.sp.SpOrderDetailService;


/**
 * 开店宝订单详情Service
 * @author aimee at 2015年6月8日下午1:53:26
 * @email 1297579898@qq.com
 */
@Service
public class SpOrderDetailServiceImpl implements SpOrderDetailService{

	@Autowired
	SpOrderDetailMgMapper spOrderDetailMgMapper;
	
	@Override
	public List<SpOrderDetail> getOrderDetail(String orderId) {
		return spOrderDetailMgMapper.getOrderDetailByOrderId(orderId);
	}

	@Override
	public List<DgSpOrderDetail> getDgOrderDetail(String orderId) {
		return null;
	}
	

}
