package com.zjjf.analysis.beans.analysis.corner;

import java.io.Serializable;

public class ErpManagerItemVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String managerId;

	private Integer itemBaseId;

	private String supplierId;

	private String managerCode;

	private String managerName;

	private String managerStatus;

	private String cooperWarehouse;

	private String cooperation;

	private String itemCode;

	private String itemName;

	private String barCode;

	private String boxCode;

	private String spec;

	private String pkg;

	private String areaPrice = "0";

	private String taxRate = "0";

	private String avgAreaPrice = "0";

	private String discountAreaPrice = "0";

	private String minAreaPrice = "0";

	private String salePrice = "0";

	private String grossProfit = "0";

	private String grossRate = "0";

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerStatus() {
		return managerStatus;
	}

	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}

	public String getCooperWarehouse() {
		return cooperWarehouse;
	}

	public void setCooperWarehouse(String cooperWarehouse) {
		this.cooperWarehouse = cooperWarehouse;
	}

	public String getCooperation() {
		return cooperation;
	}

	public void setCooperation(String cooperation) {
		this.cooperation = cooperation;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
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

	public String getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(String areaPrice) {
		this.areaPrice = areaPrice;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getAvgAreaPrice() {
		return avgAreaPrice;
	}

	public void setAvgAreaPrice(String avgAreaPrice) {
		this.avgAreaPrice = avgAreaPrice;
	}

	public String getDiscountAreaPrice() {
		return discountAreaPrice;
	}

	public void setDiscountAreaPrice(String discountAreaPrice) {
		this.discountAreaPrice = discountAreaPrice;
	}

	public String getMinAreaPrice() {
		return minAreaPrice;
	}

	public void setMinAreaPrice(String minAreaPrice) {
		this.minAreaPrice = minAreaPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}

	public String getGrossRate() {
		return grossRate;
	}

	public void setGrossRate(String grossRate) {
		this.grossRate = grossRate;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}