/**   
* @Title: ERPManagerContractRo.java 
* @Package com.corner.kefu.beans.ro.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月2日 下午4:32:27 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.erp;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.corner.core.beans.ERPManagerContract;
import com.corner.core.beans.Region;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: ERPManagerContractRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月2日 下午4:32:27 
*  
*/

public class ERPManagerContractRo extends ERPManagerContract {

	private Byte regionLevel;

	private Integer regionId;
	
	private List<Region> regionList;
	
	private String keyStr;
	
    private Integer pageIndex = CommonPageConfig.asyncPageIndex;
	
	private Integer pageSize = CommonPageConfig.asyncPageSize;

	public Byte getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(Byte regionLevel) {
		this.regionLevel = regionLevel;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public List<Region> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<Region> regionList) {
		this.regionList = regionList;
	}

	public Integer getPageIndex() {
		return pageIndex*pageSize;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyStr() {
		if(StringUtils.isNotEmpty(keyStr)){
			return keyStr.trim();
		}else{
			return null;
		}
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}
}
