package com.corner.scms.beans.vo.sc;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.ScOrderInfo;

public class ScOrderInfoMgVo extends ScOrderInfo implements Serializable {
	//商品的状态
	private Byte goodsStatus;
	//订单总箱数
	private Integer countNumber;
	//打印订单总计
	private BigDecimal printPrice;
	
	private List<ScOrderDetailVo> scOrderDetailVos = new ArrayList<ScOrderDetailVo>();

	public List<ScOrderDetailVo> getScOrderDetailVos() {
		return scOrderDetailVos;
	}

	public void setScOrderDetailVos(List<ScOrderDetailVo> scOrderDetailVos) {
		this.scOrderDetailVos = scOrderDetailVos;
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

	public Byte getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(Byte goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

}
