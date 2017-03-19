package com.zjjf.analysis.beans.vo.orders.scorder;

import java.io.Serializable;

public class ScOrderItemVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String brand;
	private String name;
	private String mdseId;
	private String spec;
	private String pkg;
	private String areaPrice;
	private String quantity;
	private String price;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getAreaPrice() {
		return areaPrice;
	}
	public void setAreaPrice(String areaPrice) {
		this.areaPrice = areaPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
