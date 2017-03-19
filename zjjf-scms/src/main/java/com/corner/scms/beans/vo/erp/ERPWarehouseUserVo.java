package com.corner.scms.beans.vo.erp;

import com.corner.core.beans.ERPManagerOrderDetail;
import com.corner.core.beans.ERPPlantItemLog;
import com.corner.core.beans.ERPWarehouseUser;

public class ERPWarehouseUserVo extends ERPWarehouseUser {
	private String whId;
	private String whName;

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}
}
