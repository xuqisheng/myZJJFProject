package com.corner.kefu.beans.vo.sp;

import java.math.BigDecimal;

import com.corner.core.beans.RechargeGrade;

public class RechargeGradeVo extends RechargeGrade {

	private BigDecimal useMoney;// 关联优惠劵面额

	private String voucherName;// 关联优惠劵名称

	
	public String getVoucherName() {
		return voucherName;
	}

	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

}
