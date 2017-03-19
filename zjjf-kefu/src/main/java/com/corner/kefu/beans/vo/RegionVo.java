/**   
* @Title: RegionVo.java 
* @Package com.corner.kefu.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年4月5日 上午9:58:39 
* @version V1.0   
*/

package com.corner.kefu.beans.vo;

import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.Region;

/** 
* @ClassName: RegionVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年4月5日 上午9:58:39 
*  
*/

public class RegionVo extends Region {

	private List<RegionVo> regionList = new ArrayList<RegionVo>();
	private String spGroupName;
	private Integer spGroupId;
	private Boolean nocheck = true;
	private Boolean checked = false;
	
    //supplier
	private String supplierId;
	private String supplierName;
	private String supplierCode;
	private String supplierAreStr;
	private String supplierMobile;

	public List<RegionVo> getRegionList() {
		return regionList;
	}

	public void setRegionList(List<RegionVo> regionList) {
		this.regionList = regionList;
	}

	public String getSpGroupName() {
		return spGroupName;
	}

	public void setSpGroupName(String spGroupName) {
		this.spGroupName = spGroupName;
	}

	public Integer getSpGroupId() {
		return spGroupId;
	}

	public void setSpGroupId(Integer spGroupId) {
		this.spGroupId = spGroupId;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierAreStr() {
		return supplierAreStr;
	}

	public void setSupplierAreStr(String supplierAreStr) {
		this.supplierAreStr = supplierAreStr;
	}

	public String getSupplierMobile() {
		return supplierMobile;
	}

	public void setSupplierMobile(String supplierMobile) {
		this.supplierMobile = supplierMobile;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
}
