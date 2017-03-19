package com.corner.kefu.service.sp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.SpOrderDetail;
import com.corner.kefu.beans.vo.sp.DgSpOrderDetail;


/**
 * 开店宝订单详情Service
 * @author aimee at 2015年6月8日下午1:53:26
 * @email 1297579898@qq.com
 */
@Service
public interface SpOrderDetailService{
	
    /**
     * 根据订单编号查询订单详情
     * @author aimee at 2015年6月8日下午1:56:21
     * @email 1297579898@qq.com
     * @param orderId
     * @return
     */
	public List<SpOrderDetail> getOrderDetail(String orderId);
	
	/**
	 * dg获取
	 * 
	 * @author luke at  2015年10月24日下午2:56:05
	 * @email tiezhongtang@izjjf.cn
	 * @param List<SpOrderDetail>
	 * @return
	 */
	public List<DgSpOrderDetail> getDgOrderDetail(String orderId) ;
}
