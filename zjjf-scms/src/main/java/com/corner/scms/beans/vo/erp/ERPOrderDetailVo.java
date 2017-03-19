package com.corner.scms.beans.vo.erp;

import com.corner.core.beans.ERPManagerOrderDetail;
import com.corner.core.beans.ERPManagerOrderInfo;
import com.corner.core.beans.ERPPlantItemLog;

import java.util.List;

public class ERPOrderDetailVo extends ERPManagerOrderDetail {
	private String mdseId;
	private String wuliu;
	private String spec;
	private String imgB;
	private String imgS;
	private String pkg;
	private String name;
	private ERPPlantItemLog erpPlantItemLog;

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ERPPlantItemLog getErpPlantItemLog() {
		return erpPlantItemLog;
	}

	public void setErpPlantItemLog(ERPPlantItemLog erpPlantItemLog) {
		this.erpPlantItemLog = erpPlantItemLog;
	}

	public String getMdseId() {
		return mdseId;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}

	public String getWuliu() {
		return wuliu;
	}

	public void setWuliu(String wuliu) {
		this.wuliu = wuliu;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getImgB() {
		return imgB;
	}

	public void setImgB(String imgB) {
		this.imgB = imgB;
	}

	public String getImgS() {
		return imgS;
	}

	public void setImgS(String imgS) {
		this.imgS = imgS;
	}

}
