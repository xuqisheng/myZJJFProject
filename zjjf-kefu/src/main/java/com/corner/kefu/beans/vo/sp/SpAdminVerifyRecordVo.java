package com.corner.kefu.beans.vo.sp;

import com.corner.core.beans.SpAdminVerifyRecord;

public class SpAdminVerifyRecordVo extends SpAdminVerifyRecord{

	private String mobile;//店主手机号
	private String storename;//商铺名称
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	
}
