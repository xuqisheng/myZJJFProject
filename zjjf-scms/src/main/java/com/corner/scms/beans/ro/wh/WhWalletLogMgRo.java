package com.corner.scms.beans.ro.wh;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;

public class WhWalletLogMgRo extends AmazeUIListRo{

	private static final long serialVersionUID = 1L;
	
	private String warehouseId;
	private Date startDate;
	private Date endDate;
	private String orderId;
	/**
	 * 1 - 今天	、	2 - 本月
	 */
	private int findType;
	public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }
    public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getFindType() {
		return findType;
	}

	public void setFindType(int findType) {
		this.findType = findType;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }
}
