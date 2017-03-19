package com.corner.scms.beans.vo.erp;

import com.corner.core.beans.ERPProfitDetail;
import com.corner.core.beans.ERPProfitInfo;

import java.io.Serializable;
import java.util.List;

public class ERPProfitVo extends ERPProfitInfo implements Serializable {
	private List<ERPProfitDetail> details;

	public List<ERPProfitDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ERPProfitDetail> details) {
		this.details = details;
	}
}
