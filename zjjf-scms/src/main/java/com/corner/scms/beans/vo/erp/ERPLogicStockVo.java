package com.corner.scms.beans.vo.erp;

import java.io.Serializable;
import java.math.BigDecimal;

import com.corner.core.beans.ERPLogicStock;

public class ERPLogicStockVo extends ERPLogicStock implements Serializable {

	private String whName;
	private String goodsName;
	private String typeMgName;
	private String mdseId;
	private String spec;
	private String pkg;
	private String itemCode;
	private String itemId;
	private String barCode;
	private BigDecimal areaPrice;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public BigDecimal getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(BigDecimal areaPrice) {
		this.areaPrice = areaPrice;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getTypeMgName() {
		return typeMgName;
	}
	public void setTypeMgName(String typeMgName) {
		this.typeMgName = typeMgName;
	}
	public String getMdseId() {
		return mdseId;
	}
	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPkg() {
		return pkg;
	}
	public void setPkg(String pkg) {
		this.pkg = pkg;
	}
}
