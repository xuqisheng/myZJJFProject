package com.corner.rpc.salesman.model;

import java.util.List;
import java.util.Map;

import com.corner.salesman.commons.persistence.BaseEntity;



public class SpGroupLine  extends BaseEntity<SpGroupLine> {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String spGroupId;

    private String spGroupName;

    private Integer isDelete;

    private String line;
    
    private String lineId;

    private String deptId;

    private String createTime;

    private String createBy;

    private String updateTime;

    private String updateBy;
    
    private String isRegister;
    
    private String shopType;
    
	private String shopId;
    
    private List<Map<String,String>> lineList = null;
    
    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;
    
    /*private Double longitude;//经度
    
    private Double latitude;//纬度

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
	}*/

	public String getLineId() {
		return lineId;
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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getIsRegister() {
		return isRegister;
	}

	public void setIsRegister(String isRegister) {
		this.isRegister = isRegister;
	}

	public List<Map<String, String>> getLineList() {
		return lineList;
	}

	public void setLineList(List<Map<String, String>> lineList) {
		this.lineList = lineList;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }
}