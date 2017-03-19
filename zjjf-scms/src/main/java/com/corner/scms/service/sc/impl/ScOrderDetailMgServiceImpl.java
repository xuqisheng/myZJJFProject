package com.corner.scms.service.sc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.msg.ModelMsg;
import com.corner.core.beans.vo.Pager;
import com.corner.scms.beans.ro.sc.ScOrderDetailRo;
import com.corner.scms.beans.vo.sc.ScOrderDetailVo;
import com.corner.scms.dao.ScOrderDetailMgMapper;
import com.corner.scms.service.sc.ScOrderDetailMgService;
@Service
public class ScOrderDetailMgServiceImpl implements ScOrderDetailMgService {
	@Autowired
	private ScOrderDetailMgMapper scOrderDetailMgMapper;

	@Override
	public ModelMsg deleteObjects(String tableName, String[] array) {
		// TODO Auto-generated method stub
		return null;
	}

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
	@Override
	public ScOrderDetailVo getOrderDetail(ScOrderDetailRo scOrderDetailRo) {
		return scOrderDetailMgMapper.getOrderDetail(scOrderDetailRo);
	}

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
	@Override
	public Pager<ScOrderDetailVo> getOrderDetailList(ScOrderDetailRo scOrderDetailRo) {
		List<ScOrderDetailVo> scOrderDetailVoList = scOrderDetailMgMapper.getOrderDetailList(scOrderDetailRo);
		int num = scOrderDetailMgMapper.getOrderDetailListCount(scOrderDetailRo);
		return new Pager<ScOrderDetailVo>(num, scOrderDetailVoList);
	}
	
	@Override
	public List<ScOrderDetail> getOrderDetailList(boolean isDelete,String id,String orderId , String orderId2 ,String maOrderInfoId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orderId", orderId);
		map.put("orderId2", orderId2);
		map.put("maOrderInfoId", maOrderInfoId);
		map.put("isDelete", isDelete);
		return scOrderDetailMgMapper.findOrderDetailList(map);
	}
}
