package com.corner.kefu.beans.ro.sp;

import com.corner.core.beans.ro.AmazeUIListRo;

public class PlantItemRo extends AmazeUIListRo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer province;
	private Integer city;
	private Integer areaid;
	private Integer spGroupId;
	private String spId;
	private String keyword;
	private String barcode;
	private String userId;
	private Integer storeId;
	private Boolean barcodeLog;
	private Integer itemCatelogid;
	private String nameAndMobile;
	
	
	public Integer getAreaid() {
		return areaid;
	}
	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}
	private String catename;
	private Integer sales;
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public Integer getItemCatelogid() {
		return itemCatelogid;
	}
	public void setItemCatelogid(Integer itemCatelogid) {
		this.itemCatelogid = itemCatelogid;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getSpGroupId() {
		return spGroupId;
	}
	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Boolean getBarcodeLog() {
		return barcodeLog;
	}
	public void setBarcodeLog(Boolean barcodeLog) {
		this.barcodeLog = barcodeLog;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public String getNameAndMobile() {
		return nameAndMobile;
	}
	public void setNameAndMobile(String nameAndMobile) {
		this.nameAndMobile = nameAndMobile;
	}
	
}
