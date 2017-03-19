package com.corner.scms.beans.ro.erp;

import java.math.BigDecimal;

public class ERPSpOrderOwnerRo{
	private Byte types[];
	private String ids[];
	private Byte cashs[];
	private Short quantitys[];
	private Short cashQuantitys[];
	private Integer itemBaseIds[];
	private BigDecimal cashPrices[];
	private Short backQuantitys[];
	private BigDecimal surePrices[];
	private Short sureQuantitys[];
	private String wh3Ids[];
	private String remarks[];
	private String whId;

	public Short[] getSureQuantitys() {
		return sureQuantitys;
	}

	public void setSureQuantitys(Short[] sureQuantitys) {
		this.sureQuantitys = sureQuantitys;
	}

	public Short[] getBackQuantitys() {
		return backQuantitys;
	}

	public void setBackQuantitys(Short[] backQuantitys) {
		this.backQuantitys = backQuantitys;
	}

	public BigDecimal[] getSurePrices() {
		return surePrices;
	}

	public void setSurePrices(BigDecimal[] surePrices) {
		this.surePrices = surePrices;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public BigDecimal[] getCashPrices() {
		return cashPrices;
	}

	public void setCashPrices(BigDecimal[] cashPrices) {
		this.cashPrices = cashPrices;
	}

	public Byte[] getTypes() {
		return types;
	}

	public void setTypes(Byte[] types) {
		this.types = types;
	}

	public Byte[] getCashs() {
		return cashs;
	}

	public void setCashs(Byte[] cashs) {
		this.cashs = cashs;
	}

	public Short[] getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(Short[] quantitys) {
		this.quantitys = quantitys;
	}

	public Short[] getCashQuantitys() {
		return cashQuantitys;
	}

	public void setCashQuantitys(Short[] cashQuantitys) {
		this.cashQuantitys = cashQuantitys;
	}

	public Integer[] getItemBaseIds() {
		return itemBaseIds;
	}

	public void setItemBaseIds(Integer[] itemBaseIds) {
		this.itemBaseIds = itemBaseIds;
	}


	public String[] getWh3Ids() {
		return wh3Ids;
	}

	public void setWh3Ids(String[] wh3Ids) {
		this.wh3Ids = wh3Ids;
	}

	public String[] getRemarks() {
		return remarks;
	}

	public void setRemarks(String[] remarks) {
		this.remarks = remarks;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}
}
