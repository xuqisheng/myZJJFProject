package com.corner.scms.beans.vo.erp;

import com.corner.core.beans.ERPPurchaseStockDetail;
import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.core.beans.ro.ABaseRo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ERPPurchaseStockVo extends ERPPurchaseStockInfo implements Serializable {
	private List<ERPPurchaseStockDetail> details;

	public List<ERPPurchaseStockDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ERPPurchaseStockDetail> details) {
		this.details = details;
	}
}
