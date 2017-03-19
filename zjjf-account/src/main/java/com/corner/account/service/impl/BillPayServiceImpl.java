package com.corner.account.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.account.beans.ro.BillPayCondition;
import com.corner.account.beans.vo.BillPayVo;
import com.corner.account.dao.BillPayMapper;
import com.corner.account.service.BillPayService;
import com.corner.core.beans.vo.Pager;

@Service
public class BillPayServiceImpl extends BaseServiceImpl implements BillPayService {

	@Autowired
	BillPayMapper billPayMapper;

	@Override
	public Pager<BillPayVo> getBillPayOverViewList(BillPayCondition command) {
		List<BillPayVo> list = billPayMapper.getPageList(command);
		DecimalFormat df = new DecimalFormat("###0.000%");
		for (Iterator<BillPayVo> iterator = list.iterator(); iterator.hasNext();) {
			BillPayVo billPayVo = iterator.next();
			if(billPayVo !=null && billPayVo.getSpZfee() !=null && billPayVo.getSpOrderPrice() !=null ){
				if(billPayVo.getSpOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
					billPayVo.setZfeeRate("0.000%");
				}else{
					BigDecimal result=billPayVo.getSpZfee().divide(billPayVo.getSpOrderPrice(),6,BigDecimal.ROUND_HALF_UP);
					billPayVo.setZfeeRate(df.format(result));
				}
			}
		}
		int size = billPayMapper.getPageListSize(command);
		return new Pager<BillPayVo>(size, list);
	}
	
	@Override
	public Pager<BillPayVo> getBillPayStatusList(BillPayCondition command) {
		List<BillPayVo> list = billPayMapper.getDetailPageList(command);
		DecimalFormat df = new DecimalFormat("###0.000%");
		for (Iterator<BillPayVo> iterator = list.iterator(); iterator.hasNext();) {
			BillPayVo billPayVo = iterator.next();
			if(billPayVo !=null && billPayVo.getSpZfee() !=null && billPayVo.getSpOrderPrice() !=null ){
				if(billPayVo.getSpOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
					billPayVo.setZfeeRate("0.000%");
				}else{
					BigDecimal result=billPayVo.getSpZfee().divide(billPayVo.getSpOrderPrice(),6,BigDecimal.ROUND_HALF_UP);
					billPayVo.setZfeeRate(df.format(result));
				}
			}
		}
		int size = billPayMapper.getDetailPageListSize(command);
		return new Pager<BillPayVo>(size, list);
	
	}

}
