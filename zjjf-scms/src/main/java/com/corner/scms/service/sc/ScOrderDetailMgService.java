package com.corner.scms.service.sc;

import java.util.List;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.sc.ScOrderDetailRo;
import com.corner.scms.beans.vo.sc.ScOrderDetailVo;
import com.corner.scms.service.BaseService;

public interface ScOrderDetailMgService extends BaseService {
	
	
	/**
	 * 获取订单明细汇总
	 * @Title
	 * @Description: TODO 
	 * @param @param scOrderDetailRo
	 * @param @return
	 * @2016年1月27日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	public ScOrderDetailVo getOrderDetail(ScOrderDetailRo scOrderDetailRo);

	/**
	 * 查询订单明细 列表
	 * @Title
	 * @Description: TODO 
	 * @param @param scOrderDetailRo
	 * @param @return
	 * @2016年1月27日     
	 * @author 龙五  longwu@izjjf.cn
	 * @return
	 * @throws
	 */
	public Pager<ScOrderDetailVo> getOrderDetailList(ScOrderDetailRo scOrderDetailRo);
	
	public List<ScOrderDetail> getOrderDetailList(boolean isDelete,String id,String orderId , String orderId2 ,String maOrderInfoId);
}
