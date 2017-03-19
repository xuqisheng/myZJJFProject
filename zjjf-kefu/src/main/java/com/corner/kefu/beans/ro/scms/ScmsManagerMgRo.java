package com.corner.kefu.beans.ro.scms;

import java.util.List;

import com.corner.core.beans.ScmsGroup;
import com.corner.core.beans.ScmsManager;

/**
 * 
* @ClassName: ScmsManagerMgRo 
* @Description: 经销商信息 
* @author 孟星魂	mengxinghun@izjjf.cn
* @date 2015年12月28日 上午11:36:35 
*
 */
public class ScmsManagerMgRo extends ScmsManager{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7791262934330106165L;
	
    private String[] groupIds;
    
    private String[] groupNames;
    private List<ScmsGroup> scmsGroups;
    private String supplierCode;
    private String isUpdate;
    
	public List<ScmsGroup> getScmsGroups() {
		return scmsGroups;
	}

	public void setScmsGroups(List<ScmsGroup> scmsGroups) {
		this.scmsGroups = scmsGroups;
	}

	public String[] getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String[] groupIds) {
		this.groupIds = groupIds;
	}

	public String[] getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String[] groupNames) {
		this.groupNames = groupNames;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode == null ? null : supplierCode.trim();
	}
	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}
}