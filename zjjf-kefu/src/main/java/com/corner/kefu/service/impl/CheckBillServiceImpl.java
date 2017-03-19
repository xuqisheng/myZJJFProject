package com.corner.kefu.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corner.core.beans.vo.Pager;
import com.corner.kefu.beans.ro.CheckBillCondition;
import com.corner.kefu.beans.vo.CheckBillVo;
import com.corner.kefu.dao.CheckBillMapper;
import com.corner.kefu.service.CheckBillService;

@Service
public class CheckBillServiceImpl extends BaseServiceImpl implements CheckBillService {

	@Autowired
	CheckBillMapper checkBillMapper;
	
	@Override
	public Pager<CheckBillVo> getCheckBillList(CheckBillCondition command) {
		List<CheckBillVo> list = checkBillMapper.getPageList(command);
		DecimalFormat df = new DecimalFormat("###0.000%");
		for (Iterator<CheckBillVo> iterator = list.iterator(); iterator.hasNext();) {
			CheckBillVo checkBillVo = iterator.next();
			if(checkBillVo !=null && checkBillVo.getSpZfee() !=null && checkBillVo.getSpOrderPrice() !=null ){
				if(checkBillVo.getSpOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
					checkBillVo.setZfeeRate("0.000%");
				}else{
					BigDecimal result=checkBillVo.getSpZfee().divide(checkBillVo.getSpOrderPrice(),3);
					checkBillVo.setZfeeRate(df.format(result));
				}
			}
		}
		int size = checkBillMapper.getPageListSize(command);
		return new Pager<CheckBillVo>(size, list);
	}

	@Override
	public Pager<CheckBillVo> getCheckBillDetailList(CheckBillCondition command) {
		List<CheckBillVo> list = checkBillMapper.getDetailPageList(command);
		DecimalFormat df = new DecimalFormat("###0.000%");
		for (Iterator<CheckBillVo> iterator = list.iterator(); iterator.hasNext();) {
			CheckBillVo checkBillVo = iterator.next();
			if(checkBillVo !=null && checkBillVo.getSpZfee() !=null && checkBillVo.getSpOrderPrice() !=null ){
				if(checkBillVo.getSpOrderPrice().compareTo(new BigDecimal(0)) == 0 ){
					checkBillVo.setZfeeRate("0.000%");
				}else{
					BigDecimal result=checkBillVo.getSpZfee().divide(checkBillVo.getSpOrderPrice(),3);
					checkBillVo.setZfeeRate(df.format(result));
				}
			}
		}
		int size = checkBillMapper.getDetailPageListSize(command);
		return new Pager<CheckBillVo>(size, list);
	}
}
