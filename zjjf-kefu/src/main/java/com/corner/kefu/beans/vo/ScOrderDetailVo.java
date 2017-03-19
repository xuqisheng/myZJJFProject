package com.corner.kefu.beans.vo;



import java.io.Serializable;
import java.math.BigDecimal;

public class ScOrderDetailVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String imgS;
	private String measure;
	private String orderId;
	private String orderId2;
	private String itemBaseId;
	private String name;
	private String pkg;
	private Integer pkgNum;
	private String mdseId;
	
	private String spec;
	
	private BigDecimal price;
	private BigDecimal zjjfPrice;
	private BigDecimal areaPrice;
	private BigDecimal marketPrice;
	private Integer quantity;
	
	private BigDecimal totalPrice;
	
	public BigDecimal getZjjfPrice() {
		return zjjfPrice;
	}

	public void setZjjfPrice(BigDecimal zjjfPrice) {
		this.zjjfPrice = zjjfPrice;
	}

	public BigDecimal getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(BigDecimal areaPrice) {
		this.areaPrice = areaPrice;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getItemBaseId() {
		return itemBaseId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setItemBaseId(String itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public Integer getPkgNum() {
		return pkgNum;
	}

	public void setPkgNum(Integer pkgNum) {
		this.pkgNum = pkgNum;
	}

	public String getImgS() {
		return imgS;
	}

	public void setImgS(String imgS) {
		this.imgS = imgS;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getOrderId2() {
		return orderId2;
	}

	public void setOrderId2(String orderId2) {
		this.orderId2 = orderId2;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
