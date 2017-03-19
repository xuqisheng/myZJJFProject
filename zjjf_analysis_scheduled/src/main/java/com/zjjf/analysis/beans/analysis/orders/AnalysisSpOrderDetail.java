package com.zjjf.analysis.beans.analysis.orders;

import java.math.BigDecimal;
import java.util.Date;

import com.zjjf.analysis.beans.origin.ParamMapVo;

public class AnalysisSpOrderDetail extends ParamMapVo {

	private Integer id;

	private String org_pk_id;

	private Integer infoId = 0;

	private String orderId;

	private String itemId;

	private String barCode;

	private String brand;

	private String name;

	private String spec;

	private String img;

	private Short quantity;

	private BigDecimal price = new BigDecimal(0);

	private BigDecimal totalPrice = new BigDecimal(0);

	private BigDecimal plantMemPrice = new BigDecimal(0);

	private Integer storeId = 0;

	private Date addTime;

	private Boolean isDelete;

	private String col1;

	private String col2;

	private String col3;

	private BigDecimal maoli = new BigDecimal(0);

	private BigDecimal fee = new BigDecimal(0);

	private BigDecimal plantDisPrice = new BigDecimal(0);

	private String orderId2;

	private Integer spGroupId = 0;

	private String spId;

	private String remark;

	private String youHui;

	private Integer restrict = 0;

	private Date updateTimeStamp;

	private Integer createTime = 0;

	private Integer itemBaseId = 0;

	private Integer status = 0;

	private Integer cityId = 0;

	private Integer areaId = 0;

	private String spGroupName;

	private String cityName;

	private String areaName;

	private String storeName;

	private String supplierName;

	private Integer dayTime = 0;

	private String gaveTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrg_pk_id() {
		return org_pk_id;
	}

	public void setOrg_pk_id(String org_pk_id) {
		this.org_pk_id = org_pk_id == null ? null : org_pk_id.trim();
	}

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId == null ? null : itemId.trim();
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode == null ? null : barCode.trim();
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand == null ? null : brand.trim();
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

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(Short quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getPlantMemPrice() {
		return plantMemPrice;
	}

	public void setPlantMemPrice(BigDecimal plantMemPrice) {
		this.plantMemPrice = plantMemPrice;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1 == null ? null : col1.trim();
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2 == null ? null : col2.trim();
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3 == null ? null : col3.trim();
	}

	public BigDecimal getMaoli() {
		return maoli;
	}

	public void setMaoli(BigDecimal maoli) {
		this.maoli = maoli;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getPlantDisPrice() {
		return plantDisPrice;
	}

	public void setPlantDisPrice(BigDecimal plantDisPrice) {
		this.plantDisPrice = plantDisPrice;
	}

	public String getOrderId2() {
		return orderId2;
	}

	public void setOrderId2(String orderId2) {
		this.orderId2 = orderId2 == null ? null : orderId2.trim();
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
		this.spId = spId == null ? null : spId.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getYouHui() {
		return youHui;
	}

	public void setYouHui(String youHui) {
		this.youHui = youHui == null ? null : youHui.trim();
	}

	public Integer getRestrict() {
		return restrict;
	}

	public void setRestrict(Integer restrict) {
		this.restrict = restrict;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getDayTime() {
		return dayTime;
	}

	public void setDayTime(Integer dayTime) {
		this.dayTime = dayTime;
	}

	public String getGaveTime() {
		return gaveTime;
	}

	public void setGaveTime(String gaveTime) {
		this.gaveTime = gaveTime;
	}

}