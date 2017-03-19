package com.corner.scms.beans.vo.erp;

import com.corner.core.beans.ERPMarketStockDetail;
import com.corner.core.beans.ERPMarketStockInfo;

import java.io.Serializable;
import java.util.List;

public class ERPMarketStockVo extends ERPMarketStockInfo implements Serializable {
	private List<ERPMarketStockDetail> details;

	public List<ERPMarketStockDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ERPMarketStockDetail> details) {
		this.details = details;
	}
}
