package com.zjjf.analysis.beans.erp.order;

import java.math.BigDecimal;
import java.util.Date;

public class ErpManagerOrderDetail {
	private String id;

	private String orderId;

	private String pkg;

	private String barCode;

	private String mdseId;

	private String itemCode;

	private String name;

	private String spec;

	private String img;

	private String itemId;

	private String managerId;

	private Integer itemBaseId;

	private BigDecimal areaPrice = new BigDecimal(0);

	private Short quantity = 0;

	private Date addTime;

	private Short operateQuantity = 0;

	private Boolean isDelete;

	private String gaveTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg == null ? null : pkg.trim();
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode == null ? null : barCode.trim();
	}

	public String getMdseId() {
		return mdseId;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId == null ? null : mdseId.trim();
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode == null ? null : itemCode.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec == null ? null : spec.trim();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId == null ? null : managerId.trim();
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public BigDecimal getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(BigDecimal areaPrice) {
		this.areaPrice = areaPrice;
	}

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Short getOperateQuantity() {
		return operateQuantity;
	}

	public void setOperateQuantity(Short operateQuantity) {
		this.operateQuantity = operateQuantity;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getGaveTime() {
		return gaveTime;
	}

	public void setGaveTime(String gaveTime) {
		this.gaveTime = gaveTime;
	}

}