/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.corner.salesman.modules.shop.entity;

import com.corner.salesman.common.persistence.DataEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 客户管理Entity
 * @author setsail
 * @version 2016-08-05
 */
public class Shop extends DataEntity<Shop> {
	
	private static final long serialVersionUID = 1L;
	private String shopId;		// 商铺ID
	private String groupId;		// groupId
	private String shopNo;		// 商铺编号
	private String shopName;		// 商铺名称
	private String telephone;		// 固定电话
	private String shopAddress;		// 详细地址
	private String shopLicense;		// shopLicense
	private String shopType;		// 商铺类型
	private String isMultipleShop;		// isMultipleShop
	private String shopArea;		// shopArea
	private String sku;		// sku
	private String staffNum;		// staffNum
	private String DistributionNum;		// DistributionNum
	private String turnover;		// turnover
	private String mainProduct;		// mainProduct
	private String saleRatio;		// saleRatio
	private String dcShop;		// dcShop
	private String baccyLicence;		// 0：表示没有；1：表示有；
	private String isPos;		// 0：表示没有；1：表示有；
	private String isComputer;		// 0：表示没有；1：表示有；
	private String isWifi;		// 0：表示没有；1：表示有；
	private String ipay;		// ipay
	private String otherPatform;		// otherPatform
	private String userId;		// userId
	private String userName;		// userName
	private String startShopHours;		// startShopHours
	private String endShopHours;		// endShopHours
	private String isDelete;		// isDelete
	private Date createTime;		// createTime
	private Date updateTime;		// updateTime
	private String province;		// 省份
	private String city;		// 城市
	private String area;		// 区域
	private String longitude;		// longitude
	private String latitude;		// latitude
	private String salesmanId;		// salesmanId
	private String salesmanName;		// salesmanName
	private String picUrl;		// picUrl
	private String spGroupId;		// 定格ID
	private String spGroupName;		// 定格名称
	private String bossName;		// 联系人
	private String bossTel;		// 负责人电话
	private String registerTel;		// 注册电话
	private String isRegister;		// 是否注册
	private String markLine;		// 标记路线
	private String shopAdderss;		// shopAdderss
	private String provinceId;		// provinceId
	private String cityId;		// cityId
	private String areaId;		// areaId
	private String shopIds;
	private String isAllot;
	
	public Shop() {
		super();
	}

	public Shop(String id){
		super(id);
	}

	public String getIsAllot() {
		return isAllot;
	}

	public void setIsAllot(String isAllot) {
		this.isAllot = isAllot;
	}

	@Length(min=1, max=32, message="商铺ID长度必须介于 1 和 32 之间")
	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	public String getShopIds() {
		return shopIds;
	}

	public void setShopIds(String shopIds) {
		this.shopIds = shopIds;
	}

	@Length(min=0, max=20, message="groupId长度必须介于 0 和 20 之间")
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=0, max=32, message="商铺编号长度必须介于 0 和 32 之间")
	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	
	@Length(min=0, max=100, message="商铺名称长度必须介于 0 和 100 之间")
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	@Length(min=0, max=20, message="固定电话长度必须介于 0 和 20 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=200, message="详细地址长度必须介于 0 和 200 之间")
	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	
	@Length(min=0, max=32, message="shopLicense长度必须介于 0 和 32 之间")
	public String getShopLicense() {
		return shopLicense;
	}

	public void setShopLicense(String shopLicense) {
		this.shopLicense = shopLicense;
	}
	
	@Length(min=0, max=50, message="商铺类型长度必须介于 0 和 50 之间")
	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	
	@Length(min=0, max=11, message="isMultipleShop长度必须介于 0 和 11 之间")
	public String getIsMultipleShop() {
		return isMultipleShop;
	}

	public void setIsMultipleShop(String isMultipleShop) {
		this.isMultipleShop = isMultipleShop;
	}
	
	@Length(min=0, max=11, message="shopArea长度必须介于 0 和 11 之间")
	public String getShopArea() {
		return shopArea;
	}

	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}
	
	@Length(min=0, max=11, message="sku长度必须介于 0 和 11 之间")
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	@Length(min=0, max=11, message="staffNum长度必须介于 0 和 11 之间")
	public String getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}
	
	@Length(min=0, max=11, message="DistributionNum长度必须介于 0 和 11 之间")
	public String getDistributionNum() {
		return DistributionNum;
	}

	public void setDistributionNum(String DistributionNum) {
		this.DistributionNum = DistributionNum;
	}
	
	@Length(min=0, max=11, message="turnover长度必须介于 0 和 11 之间")
	public String getTurnover() {
		return turnover;
	}

	public void setTurnover(String turnover) {
		this.turnover = turnover;
	}
	
	@Length(min=0, max=200, message="mainProduct长度必须介于 0 和 200 之间")
	public String getMainProduct() {
		return mainProduct;
	}

	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}
	
	@Length(min=0, max=11, message="saleRatio长度必须介于 0 和 11 之间")
	public String getSaleRatio() {
		return saleRatio;
	}

	public void setSaleRatio(String saleRatio) {
		this.saleRatio = saleRatio;
	}
	
	@Length(min=0, max=100, message="dcShop长度必须介于 0 和 100 之间")
	public String getDcShop() {
		return dcShop;
	}

	public void setDcShop(String dcShop) {
		this.dcShop = dcShop;
	}
	
	@Length(min=0, max=11, message="0：表示没有；1：表示有；长度必须介于 0 和 11 之间")
	public String getBaccyLicence() {
		return baccyLicence;
	}

	public void setBaccyLicence(String baccyLicence) {
		this.baccyLicence = baccyLicence;
	}
	
	@Length(min=0, max=11, message="0：表示没有；1：表示有；长度必须介于 0 和 11 之间")
	public String getIsPos() {
		return isPos;
	}

	public void setIsPos(String isPos) {
		this.isPos = isPos;
	}
	
	@Length(min=0, max=11, message="0：表示没有；1：表示有；长度必须介于 0 和 11 之间")
	public String getIsComputer() {
		return isComputer;
	}

	public void setIsComputer(String isComputer) {
		this.isComputer = isComputer;
	}
	
	@Length(min=0, max=11, message="0：表示没有；1：表示有；长度必须介于 0 和 11 之间")
	public String getIsWifi() {
		return isWifi;
	}

	public void setIsWifi(String isWifi) {
		this.isWifi = isWifi;
	}
	
	@Length(min=0, max=200, message="ipay长度必须介于 0 和 200 之间")
	public String getIpay() {
		return ipay;
	}

	public void setIpay(String ipay) {
		this.ipay = ipay;
	}
	
	@Length(min=0, max=200, message="otherPatform长度必须介于 0 和 200 之间")
	public String getOtherPatform() {
		return otherPatform;
	}

	public void setOtherPatform(String otherPatform) {
		this.otherPatform = otherPatform;
	}
	
	@Length(min=0, max=32, message="userId长度必须介于 0 和 32 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=32, message="userName长度必须介于 0 和 32 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=10, message="startShopHours长度必须介于 0 和 10 之间")
	public String getStartShopHours() {
		return startShopHours;
	}

	public void setStartShopHours(String startShopHours) {
		this.startShopHours = startShopHours;
	}
	
	@Length(min=0, max=10, message="endShopHours长度必须介于 0 和 10 之间")
	public String getEndShopHours() {
		return endShopHours;
	}

	public void setEndShopHours(String endShopHours) {
		this.endShopHours = endShopHours;
	}
	
	@Length(min=0, max=1, message="isDelete长度必须介于 0 和 1 之间")
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Length(min=0, max=100, message="省份长度必须介于 0 和 100 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=100, message="城市长度必须介于 0 和 100 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=100, message="区域长度必须介于 0 和 100 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Length(min=0, max=32, message="salesmanId长度必须介于 0 和 32 之间")
	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}
	
	@Length(min=0, max=50, message="salesmanName长度必须介于 0 和 50 之间")
	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}
	
	@Length(min=0, max=1000, message="picUrl长度必须介于 0 和 1000 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=11, message="定格ID长度必须介于 0 和 11 之间")
	public String getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(String spGroupId) {
		this.spGroupId = spGroupId;
	}
	
	@Length(min=0, max=50, message="定格名称长度必须介于 0 和 50 之间")
	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}
	
	@Length(min=0, max=32, message="联系人长度必须介于 0 和 32 之间")
	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	
	@Length(min=0, max=15, message="负责人电话长度必须介于 0 和 15 之间")
	public String getBossTel() {
		return bossTel;
	}

	public void setBossTel(String bossTel) {
		this.bossTel = bossTel;
	}
	
	@Length(min=0, max=15, message="注册电话长度必须介于 0 和 15 之间")
	public String getRegisterTel() {
		return registerTel;
	}

	public void setRegisterTel(String registerTel) {
		this.registerTel = registerTel;
	}
	
	@Length(min=0, max=1, message="是否注册长度必须介于 0 和 1 之间")
	public String getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}
	
	@Length(min=0, max=50, message="标记路线长度必须介于 0 和 50 之间")
	public String getMarkLine() {
		return markLine;
	}

	public void setMarkLine(String markLine) {
		this.markLine = markLine;
	}
	
	@Length(min=0, max=200, message="shopAdderss长度必须介于 0 和 200 之间")
	public String getShopAdderss() {
		return shopAdderss;
	}

	public void setShopAdderss(String shopAdderss) {
		this.shopAdderss = shopAdderss;
	}
	
	@Length(min=0, max=11, message="provinceId长度必须介于 0 和 11 之间")
	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	
	@Length(min=0, max=11, message="cityId长度必须介于 0 和 11 之间")
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
	@Length(min=0, max=11, message="areaId长度必须介于 0 和 11 之间")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	
}