package com.corner.rpc.salesman.model;

import java.util.List;

import com.corner.salesman.commons.persistence.BaseEntity;

public class Shop extends BaseEntity<Shop> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer storeId;
	
	private String shopId;

    private String groupId;

    private String shopNo;

    private String shopName;

    private String telephone;

    private String shopAddress;

    private String shopLicense;

    private String shopType;
    
    private String shopTypeName;//门店类型名称

    private String isMultipleShop;

    private String shopArea;

    private String sku;

    private String staffNum;

    private String distributionNum;

    private String turnover;

    private String mainProduct;

    private String saleRatio;

    private String dcShop;

    private String baccyLicence;

    private String isPos;

    private String isComputer;

    private String isWifi;

    private String ipay;

    private String otherPatform;

    private String remarks;

    private String startShopHours;

    private String endShopHours;

    private Integer isDelete;

    private String createTime;

    private String updateTime;

    private String createBy;

    private String updateBy;

    private String province;

    private String city;

    private String area;

    private Double longitude;

    private Double latitude;

    private String salesmanId;

    private String salesmanName;

    private String picUrl;

    private String spGroupId;

    private String spGroupName;

    private String bossName;

    private String bossTel;

    private String registerTel;

    private String isRegister;

    private String markLine;
    
    private String lineId;
    private String lineName;

    private String shopAdderss;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;
    
    public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	private String status;
    
	private String shopIds;
	
	private String week;
	
	private String salesmanTel;
	
	private String optType;
    
    public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getSalesmanTel() {
		return salesmanTel == null?"":salesmanTel;
	}

	public void setSalesmanTel(String salesmanTel) {
		this.salesmanTel = salesmanTel;
	}

	public String getStatus() {
		return status;
	}

	public String getShopIds() {
		return shopIds;
	}

	public void setShopIds(String shopIds) {
		this.shopIds = shopIds;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@SuppressWarnings("rawtypes")
	private List picList = null;//图片列表
    
    @SuppressWarnings("rawtypes")
	public List getPicList() {
		return picList;
	}

	@SuppressWarnings("rawtypes")
	public void setPicList(List picList) {
		this.picList = picList;
	}

	/*public Integer getTmpProvinceId() {
		return tmpProvinceId;
	}

	public void setTmpProvinceId(Integer tmpProvinceId) {
		this.tmpProvinceId = tmpProvinceId;
	}

	public Integer getTmpCityId() {
		return tmpCityId;
	}

	public void setTmpCityId(Integer tmpCityId) {
		this.tmpCityId = tmpCityId;
	}

	public Integer getTmpAreaId() {
		return tmpAreaId;
	}

	public void setTmpAreaId(Integer tmpAreaId) {
		this.tmpAreaId = tmpAreaId;
	}

	public String getTmpSpGroupId() {
		return tmpSpGroupId;
	}

	public void setTmpSpGroupId(String tmpSpGroupId) {
		this.tmpSpGroupId = tmpSpGroupId;
	}

	public String getTmpShopName() {
		return tmpShopName;
	}

	public void setTmpShopName(String tmpShopName) {
		this.tmpShopName = tmpShopName;
	}

	public String getTmpBossTel() {
		return tmpBossTel;
	}

	public void setTmpBossTel(String tmpBossTel) {
		this.tmpBossTel = tmpBossTel;
	}

	public String getTmpBossName() {
		return tmpBossName;
	}

	public void setTmpBossName(String tmpBossName) {
		this.tmpBossName = tmpBossName;
	}

	public String getTmpShopAddress() {
		return tmpShopAddress;
	}

	public void setTmpShopAddress(String tmpShopAddress) {
		this.tmpShopAddress = tmpShopAddress;
	}*/

	public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress == null ? null : shopAddress.trim();
    }

    public String getShopLicense() {
        return shopLicense;
    }

    public void setShopLicense(String shopLicense) {
        this.shopLicense = shopLicense == null ? null : shopLicense.trim();
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
    }

    public String getShopTypeName() {
		return shopTypeName;
	}

	public void setShopTypeName(String shopTypeName) {
		this.shopTypeName = shopTypeName;
	}

	public String getIsMultipleShop() {
        return isMultipleShop;
    }

    public void setIsMultipleShop(String isMultipleShop) {
        this.isMultipleShop = isMultipleShop;
    }

    public String getShopArea() {
        return shopArea;
    }

    public void setShopArea(String shopArea) {
        this.shopArea = shopArea == null ? null : shopArea.trim();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum == null ? null : staffNum.trim();
    }

    public String getDistributionNum() {
        return distributionNum;
    }

    public void setDistributionNum(String distributionNum) {
        this.distributionNum = distributionNum == null ? null : distributionNum.trim();
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover == null ? null : turnover.trim();
    }

    public String getMainProduct() {
        return mainProduct;
    }

    public void setMainProduct(String mainProduct) {
        this.mainProduct = mainProduct == null ? null : mainProduct.trim();
    }

    public String getSaleRatio() {
        return saleRatio;
    }

    public void setSaleRatio(String saleRatio) {
        this.saleRatio = saleRatio;
    }

    public String getDcShop() {
        return dcShop;
    }

    public void setDcShop(String dcShop) {
        this.dcShop = dcShop == null ? null : dcShop.trim();
    }

    public String getBaccyLicence() {
        return baccyLicence;
    }

    public void setBaccyLicence(String baccyLicence) {
        this.baccyLicence = baccyLicence;
    }

    public String getIsPos() {
        return isPos;
    }

    public void setIsPos(String isPos) {
        this.isPos = isPos;
    }

    public String getIsComputer() {
        return isComputer;
    }

    public void setIsComputer(String isComputer) {
        this.isComputer = isComputer;
    }

    public String getIsWifi() {
        return isWifi;
    }

    public void setIsWifi(String isWifi) {
        this.isWifi = isWifi;
    }

    public String getIpay() {
        return ipay;
    }

    public void setIpay(String ipay) {
        this.ipay = ipay == null ? null : ipay.trim();
    }

    public String getOtherPatform() {
        return otherPatform;
    }

    public void setOtherPatform(String otherPatform) {
        this.otherPatform = otherPatform == null ? null : otherPatform.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getStartShopHours() {
        return startShopHours;
    }

    public void setStartShopHours(String startShopHours) {
        this.startShopHours = startShopHours == null ? null : startShopHours.trim();
    }

    public String getEndShopHours() {
        return endShopHours;
    }

    public void setEndShopHours(String endShopHours) {
        this.endShopHours = endShopHours == null ? null : endShopHours.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(String salesmanId) {
        this.salesmanId = salesmanId == null ? null : salesmanId.trim();
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName == null ? null : salesmanName.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(String spGroupId) {
        this.spGroupId = spGroupId;
    }

    public String getSpGroupName() {
        return spGroupName;
    }

    public void setSpGroupName(String spGroupName) {
        this.spGroupName = spGroupName == null ? null : spGroupName.trim();
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName == null ? null : bossName.trim();
    }

    public String getBossTel() {
        return bossTel;
    }

    public void setBossTel(String bossTel) {
        this.bossTel = bossTel == null ? null : bossTel.trim();
    }

    public String getRegisterTel() {
        return registerTel;
    }

    public void setRegisterTel(String registerTel) {
        this.registerTel = registerTel == null ? null : registerTel.trim();
    }

    public String getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(String isRegister) {
        this.isRegister = isRegister == null ? null : isRegister.trim();
    }

    public String getMarkLine() {
        return markLine;
    }

    public void setMarkLine(String markLine) {
        this.markLine = markLine == null ? null : markLine.trim();
    }

    public String getShopAdderss() {
        return shopAdderss;
    }

    public void setShopAdderss(String shopAdderss) {
        this.shopAdderss = shopAdderss == null ? null : shopAdderss.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
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
}