package com.corner.kefu.service.scms.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.core.dao.ScOrderDetailMapper;
import com.corner.core.dao.ScOrderInfoMapper;
import com.corner.kefu.beans.ro.scms.ScmsPurchaseRo;
import com.corner.kefu.beans.vo.ScOrderDetailVo;
import com.corner.kefu.beans.vo.ScOrderInfoVo;
import com.corner.kefu.dao.ScOrderInfoMgMapper;
import com.corner.kefu.service.scms.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService{

	@Autowired
	private ScOrderInfoMapper infoMapper;
	@Autowired
	private ScOrderInfoMgMapper infoMgMapper;
	
	@Autowired
	private ScOrderDetailMapper detailMapper;
	
	@Override
	public Pager<ScOrderInfoVo> findAllOrderInfo(ScmsPurchaseRo condition) {
		if(condition.getPageIndex()<0){
			condition.setPageIndex(0);
		}
		if(condition.getPageSize()<=0){
			condition.setPageSize(10);
		}
		List<ScOrderInfoVo> list=this.infoMgMapper.getList(condition);
		int size=this.infoMgMapper.getSize(condition);
		return new Pager<ScOrderInfoVo>(size, list);
	}

	@Override
	public ScOrderInfoVo findOrderByOid(String orderId) {
		return this.infoMgMapper.findOrderByOid(orderId);
	}

	@Override
	public List<ScOrderDetailVo> findOrderDetail(String orderId) {
		return this.infoMgMapper.findOrderDetail(orderId) ;
	}
}
