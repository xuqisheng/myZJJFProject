package com.corner.scms.beans.ro.sc;

import com.corner.core.beans.ro.AmazeUIListRo;

public class MaOrderInfoMgRo extends AmazeUIListRo {
	
	private static final long serialVersionUID = 1L;
	//分页条件
	private int pageIndex ;
	//默认加载2条
	private int pageSize = 2;
	//排序字段
	private String sortName;
	//排序方式（asc or desc）
	private String sortManner;
	//查询时用的状态
	private Byte queryStatus;
	//订单入库管理页面的收索条件
	private String orderIdAndSupply;
	//配送单页面的收索条件
	private String param;
	private String warehouseId;
	private String managerId;
	private Byte whPayStatus;
	public Byte getWhPayStatus() {
		return whPayStatus;
	}

	public void setWhPayStatus(Byte whPayStatus) {
		this.whPayStatus = whPayStatus;
	}
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	public Byte getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(Byte queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getOrderIdAndSupply() {
		return orderIdAndSupply;
	}

	public void setOrderIdAndSupply(String orderIdAndSupply) {
		this.orderIdAndSupply = orderIdAndSupply;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}


}
