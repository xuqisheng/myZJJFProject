package com.corner.kefu.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.BillSettleCondition;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.vo.BillSettleVo;
import com.corner.kefu.dao.BillSettleMapper;
import com.corner.kefu.service.BillSettleService;

@Service
public class BillSettleServiceImpl extends BaseServiceImpl implements BillSettleService {

	@Autowired
	BillSettleMapper billSettleMapper;

	@Override
	public Pager<BillSettleVo> getBillSettleList(BillSettleCondition command) {
		List<BillSettleVo> list = billSettleMapper.getPageList(command);
		DecimalFormat df = new DecimalFormat("###0.000%");
		for (Iterator<BillSettleVo> iterator = list.iterator(); iterator.hasNext();) {
			BillSettleVo billSettleVo = iterator.next();
			if(billSettleVo !=null && billSettleVo.getSpZfee() !=null && billSettleVo.getSpOrderPrice() !=null ){
				if(billSettleVo.getSpOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
					billSettleVo.setZfeeRate("0.000%");
				}else{
					BigDecimal result=billSettleVo.getSpZfee().divide(billSettleVo.getSpOrderPrice(),3);
					billSettleVo.setZfeeRate(df.format(result));
				}
			}
		}
		int size = billSettleMapper.getPageListSize(command);
		return new Pager<BillSettleVo>(size, list);
	}

	@Override
	public Pager<BillSettleVo> getBillSettleOrderList(CheckBillCondition command) {

		List<BillSettleVo> list = billSettleMapper.getDetailPageList(command);
		DecimalFormat df = new DecimalFormat("###0.000%");
		for (Iterator<BillSettleVo> iterator = list.iterator(); iterator.hasNext();) {
			BillSettleVo billSettleVo = iterator.next();
			if(billSettleVo !=null && billSettleVo.getSpZfee() !=null && billSettleVo.getSpOrderPrice() !=null ){
				if(billSettleVo.getSpOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
					billSettleVo.setZfeeRate("0.000%");
				}else{
					BigDecimal result=billSettleVo.getSpZfee().divide(billSettleVo.getSpOrderPrice(),3);
					billSettleVo.setZfeeRate(df.format(result));
				}
			}
		}
		int size = billSettleMapper.getDetailPageListSize(command);
		return new Pager<BillSettleVo>(size, list);
	
	}


}
