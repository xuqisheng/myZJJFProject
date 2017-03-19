package com.corner.scms.beans.vo.erp;

import java.io.Serializable;
import java.util.Date;

import com.corner.core.beans.ERPPhysicsStock;
import com.corner.core.utils.DateUtil;

public class ERPPhysicsStockVo extends ERPPhysicsStock implements Serializable {
	private String logicStockId;
	
	private Boolean isExist;//是否存在
	
	private String typeMgName;
	
	private String whName;//仓库名
	
	private String waName;//库区名
	
	private String wpName;//库位名
	
	public String getLogicStockId() {
		return logicStockId;
	}

	public void setLogicStockId(String logicStockId) {
		this.logicStockId = logicStockId;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getWaName() {
		return waName;
	}

	public void setWaName(String waName) {
		this.waName = waName;
	}

	public String getWpName() {
		return wpName;
	}

	public void setWpName(String wpName) {
		this.wpName = wpName;
	}

	public Boolean getIsExist() {
		return isExist;
	}

	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}

	public String getTypeMgName() {
		return typeMgName;
	}

	public void setTypeMgName(String typeMgName) {
		this.typeMgName = typeMgName;
	}
	public String getProductionTimeStr() {
		return DateUtil.date2String(this.getProductionTime() == null ? new Date() : this.getProductionTime());
	}
}
