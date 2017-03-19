package com.corner.scms.beans.vo.sc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.MaOrderInfo;

public class MaOrderInfoMgVo extends MaOrderInfo implements Serializable {

	//订单总箱数
	private Integer countNumber;
	//打印订单总计
	private BigDecimal printPrice;
	//订单详情list
	private List<ScOrderDetailVo> ScOrderDetailVoList = new ArrayList<ScOrderDetailVo>();
	
	

	public List<ScOrderDetailVo> getScOrderDetailVoList() {
		return ScOrderDetailVoList;
	}

	public void setScOrderDetailVoList(List<ScOrderDetailVo> scOrderDetailVoList) {
		ScOrderDetailVoList = scOrderDetailVoList;
	}

	public Integer getCountNumber() {
		return countNumber;
	}

	public void setCountNumber(Integer countNumber) {
		this.countNumber = countNumber;
	}

	public BigDecimal getPrintPrice() {
		return printPrice;
	}

	public void setPrintPrice(BigDecimal printPrice) {
		this.printPrice = printPrice;
	}
}
