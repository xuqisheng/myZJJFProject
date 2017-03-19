package com.zjjf.analysis.beans.analysis.supplier;

public class SupplierIsZj {

	private Integer id;

	private String supplierId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId == null ? null : supplierId.trim();
	}
}