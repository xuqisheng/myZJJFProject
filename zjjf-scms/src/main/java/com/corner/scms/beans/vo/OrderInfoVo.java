package com.corner.scms.beans.vo;

import java.io.Serializable;

import com.corner.core.beans.SpOrderInfo;

public class OrderInfoVo extends SpOrderInfo implements Serializable {
	//省市区名称字符串
    private String ssqName;
    
    private String addressRemark;

	public String getSsqName() {
		return ssqName;
	}

	public void setSsqName(String ssqName) {
		this.ssqName = ssqName;
	}

	public String getAddressRemark() {
		return addressRemark;
	}

	public void setAddressRemark(String addressRemark) {
		this.addressRemark = addressRemark;
	}
	

}
