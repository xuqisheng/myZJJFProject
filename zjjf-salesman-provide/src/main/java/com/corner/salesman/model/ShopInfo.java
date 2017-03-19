package com.corner.salesman.model;

import java.util.List;

import com.corner.salesman.common.persistence.DataEntity;

public class ShopInfo extends DataEntity<ShopInfo> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String shopId;//商铺ID

    private String groupId;//用户组

    private String shopNo;//门店编码

    private String shopName;//门店名称

    private String telephone;//联系电话

    private String province;//省份
    private String city;//城市
    private String area;//区域
    
    private Integer provinceId;//省份ID
    private Integer cityId;//城市ID
    private Integer areaId;//区域ID

    private String shopAddress;//门店地址

    private String shopLicense;//门店许可证

    private String shopType;//门店类型
    private String shopTypeName;//门店类型名称

    private String isMultipleShop;//是否连锁

    private String shopArea;//商铺面积

    private String sku;//库存

    private String staffNum;//人员数量

    private String distributionNum;//配送人员数量

    private String turnover;//日均营业额

    private String mainProduct;//主营商品

    private String saleRatio;//主营销售占比

    private String dcShop;//配送商

    private String baccyLicence;//烟草许可证

    private String isPos;//POS机

    private String isComputer;//电脑

    private String isWifi;//WIFI

    private String ipay;//支付平台

    private String otherPatform;//其他合作平台

    private String remarks;//备注

    private String userId;//当前用户ID
    
    private String startShopHours;//营业开始时间

    private String endShopHours;//营业结束时间

    private Integer isDelete;//是否删除
    
    private Double longitude;

    private Double latitude;
    
    @SuppressWarnings("rawtypes")
	private List picList = null;//图片列表
    
    private String picUrl;//图片url
    
    private String markLine;
    private String isRegister;
    private String spGroupId;
    private String spGroupName;
    private String lineId;
    
    private String bossName;
    private String bossTel;
    private String registerTel;
    
    //===========考虑1.6版本后可以删除的字段======================
    private String salesmanId;
    private String salesmanName;
    private String shopAdderss;
    
    public String getShopTypeName() {
		return shopTypeName;
	}

	public void setShopTypeName(String shopTypeName) {
		this.shopTypeName = shopTypeName;
	}

	public String getShopAdderss() {
		return shopAdderss;
	}

	public void setShopAdderss(String shopAdderss) {
		this.shopAdderss = shopAdderss;
	}

	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}

	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getMarkLine() {
		return markLine;
	}

	public void setMarkLine(String markLine) {
		this.markLine = markLine;
	}

	public String getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
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
		this.spGroupName = spGroupName;
	}

	public String getBossName() {
		return bossName;
	}

	public void setBossName(String bossName) {
		this.bossName = bossName;
	}

	public void setBossTel(String bossTel) {
		this.bossTel = bossTel;
	}

	public String getRegisterTel() {
		return registerTel;
	}

	public void setRegisterTel(String registerTel) {
		this.registerTel = registerTel;
	}

	@SuppressWarnings("rawtypes")
	public List getPicList() {
		return picList;
	}

	@SuppressWarnings("rawtypes")
	public void setPicList(List picList) {
		this.picList = picList;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
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
		this.shopArea = shopArea;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(String staffNum) {
		this.staffNum = staffNum;
	}

	public String getDistributionNum() {
		return distributionNum;
	}

	public void setDistributionNum(String distributionNum) {
		this.distributionNum = distributionNum;
	}

	public String getTurnover() {
		return turnover;
	}

	public void setTurnover(String turnover) {
		this.turnover = turnover;
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
        this.otherPatform = otherPatform;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public String getBossTel() {
		return bossTel;
	}
    
}