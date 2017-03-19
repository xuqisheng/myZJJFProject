package com.corner.scms.beans.ro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.ERPPlantItemLog;

public class EditParamRo implements Serializable {
	private List<ERPPlantItemLog> erpPlantItemLogs = new ArrayList<ERPPlantItemLog>();

	public List<ERPPlantItemLog> getErpPlantItemLogs() {
		return erpPlantItemLogs;
	}

	public void setErpPlantItemLogs(List<ERPPlantItemLog> erpPlantItemLogs) {
		this.erpPlantItemLogs = erpPlantItemLogs;
	}
	
}
