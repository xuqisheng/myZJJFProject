package com.corner.salesman.model;

import java.util.Date;
import java.util.List;

import com.corner.core.beans.ro.ABaseRo;

public class SpGroupData extends ABaseRo {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String spGroupId;
	
	private String opType;//操作类型

    private String spGroupName;

    private Integer isDelete;

    private String line;

    private String deptId;
    
    private String deptName;

    private Date createTime;

    private String createBy;
    
    private String dbUsers;//DB专员
    
    private Date updateTime;

    private String updateBy;
    
    private String userIdSet;//用户ID集合
    
    private String lineSet;//设置路线集合
    
    private String lineId;//线路ID
    
    private String userId;//用户ID
    
    private String shopId;//店铺ID
    
    private String shopName;//店铺名称
    
    private List<Shop> shop;//店铺对象（供路线加载数据使用）
    
    private Integer provinceId;
    
    private Integer cityId;
    
    private Integer areaId;
    
    private Integer hisProvinceId;//原来的省份ID
    
    private Integer hisCityId;////原来的城市ID
    
    private Integer hisAreaId;////原来的区域ID
    
	public Integer getHisProvinceId() {
		return hisProvinceId;
	}

	public void setHisProvinceId(Integer hisProvinceId) {
		this.hisProvinceId = hisProvinceId;
	}

	public Integer getHisCityId() {
		return hisCityId;
	}

	public void setHisCityId(Integer hisCityId) {
		this.hisCityId = hisCityId;
	}

	public Integer getHisAreaId() {
		return hisAreaId;
	}

	public void setHisAreaId(Integer hisAreaId) {
		this.hisAreaId = hisAreaId;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
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
	public List<Shop> getShop() {
		return shop;
	}

	public void setShop(List<Shop> shop) {
		this.shop = shop;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIdSet() {
		return userIdSet;
	}

	public void setUserIdSet(String userIdSet) {
		this.userIdSet = userIdSet;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getLineSet() {
		return lineSet;
	}

	public void setLineSet(String lineSet) {
		this.lineSet = lineSet;
	}

	public String getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(String spGroupId) {
        this.spGroupId = spGroupId == null ? null : spGroupId.trim();
    }

    public String getSpGroupName() {
        return spGroupName;
    }

    public void setSpGroupName(String spGroupName) {
        this.spGroupName = spGroupName == null ? null : spGroupName.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line == null ? null : line.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDbUsers() {
		return dbUsers;
	}

	public void setDbUsers(String dbUsers) {
		this.dbUsers = dbUsers;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}