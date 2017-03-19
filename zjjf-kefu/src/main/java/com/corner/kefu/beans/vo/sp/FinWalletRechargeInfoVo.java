package com.corner.kefu.beans.vo.sp;

import java.io.Serializable;

import com.corner.core.beans.FinWalletRechargeInfo;

public class FinWalletRechargeInfoVo extends FinWalletRechargeInfo implements Serializable {
	private String mobile;
	private String ruleName;
	private String userTypeStr;
	private String supplierId;
	private String storeId;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getUserTypeStr() {
		return userTypeStr;
	}

	public void setUserTypeStr(String userTypeStr) {
		this.userTypeStr = userTypeStr;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}
