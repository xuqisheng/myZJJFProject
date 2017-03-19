package com.corner.account.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.account.beans.ro.BillSettleCondition;
import com.corner.account.beans.vo.BillSettleVo;
import com.corner.account.dao.BillSettleMapper;
import com.corner.account.service.BillSettleService;
import com.corner.core.beans.vo.Pager;

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
					BigDecimal result=billSettleVo.getSpZfee().divide(billSettleVo.getSpOrderPrice(),6,BigDecimal.ROUND_HALF_UP);
					billSettleVo.setZfeeRate(df.format(result));
				}
			}
		}
		int size = billSettleMapper.getPageListSize(command);
		return new Pager<BillSettleVo>(size, list);
	}

	@Override
	public Pager<BillSettleVo> getBillSettleOrderList(BillSettleCondition command) {

		List<BillSettleVo> list = billSettleMapper.getDetailPageList(command);
		DecimalFormat df = new DecimalFormat("###0.000%");
		for (Iterator<BillSettleVo> iterator = list.iterator(); iterator.hasNext();) {
			BillSettleVo billSettleVo = iterator.next();
			if(billSettleVo !=null && billSettleVo.getSpZfee() !=null && billSettleVo.getSpOrderPrice() !=null ){
				if(billSettleVo.getSpOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
					billSettleVo.setZfeeRate("0.000%");
				}else{
					BigDecimal result=billSettleVo.getSpZfee().divide(billSettleVo.getSpOrderPrice(),6,BigDecimal.ROUND_HALF_UP);
					billSettleVo.setZfeeRate(df.format(result));
				}
			}
		}
		int size = billSettleMapper.getDetailPageListSize(command);
		return new Pager<BillSettleVo>(size, list);
	
	}


}
