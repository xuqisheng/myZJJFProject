package com.corner.core.beans.ro;


public class AppCodeRo extends ABaseRo{
 
	private static final long serialVersionUID = 1L;
	
	private Integer width;
	private Integer height;
	private Integer rage=5;
	private String content;
	private String orderId;
	private String userId;
	private String storeId;
	private String storeName;
	
	
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getRage() {
		return rage;
	}
	public void setRage(Integer rage) {
		this.rage = rage;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
}
