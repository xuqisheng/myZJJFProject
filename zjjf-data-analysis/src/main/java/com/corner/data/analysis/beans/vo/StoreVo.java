package com.corner.data.analysis.beans.vo;

import com.corner.core.beans.ro.ABaseRo;

public class StoreVo extends ABaseRo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//店铺ID
	private String name;//店铺名称
	private String contact;//店铺老板
	private String areaId;//区域ID
	private String areaName;//区域名称
	private String shopTel;//店铺电话
	private String address;//店铺地址
	private String addTime;//注册时间
	private String shopRatedName;//商铺定格
	private String yewuRenTel;//业务员电话
	private String yewuRenName;//业务员
	private String status;//店铺状态 
	private String licenseNum;//店铺许可证
	private Integer isDelete;//是否删除（0：表示正常；1表示删除）
	private String startTime;//查询开始时间
	private String endTime;//查询结束时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getShopTel() {
		return shopTel;
	}
	public void setShopTel(String shopTel) {
		this.shopTel = shopTel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getShopRatedName() {
		return shopRatedName;
	}
	public void setShopRatedName(String shopRatedName) {
		this.shopRatedName = shopRatedName;
	}
	public String getYewuRenTel() {
		return yewuRenTel;
	}
	public void setYewuRenTel(String yewuRenTel) {
		this.yewuRenTel = yewuRenTel;
	}
	public String getYewuRenName() {
		return yewuRenName;
	}
	public void setYewuRenName(String yewuRenName) {
		this.yewuRenName = yewuRenName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
}
