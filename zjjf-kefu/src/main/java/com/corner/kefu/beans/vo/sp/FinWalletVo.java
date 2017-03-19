package com.corner.kefu.beans.vo.sp;

import com.corner.core.beans.FinWallet;

public class FinWalletVo extends FinWallet {
	private String userName;// 关联用户名称

	private String mobile;// 管理用户手机号

	private String commonId;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCommonId() {
		return commonId;
	}

	public void setCommonId(String commonId) {
		this.commonId = commonId;
	}
}
