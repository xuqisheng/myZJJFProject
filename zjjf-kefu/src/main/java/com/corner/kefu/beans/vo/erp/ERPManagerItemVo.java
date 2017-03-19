/**   
* @Title: ERPManagerItemVo.java 
* @Package com.corner.kefu.beans.vo.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月5日 上午9:57:48 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.erp;

import com.corner.core.beans.ERPManagerItem;

/** 
* @ClassName: ERPManagerItemVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月5日 上午9:57:48 
*  
*/

public class ERPManagerItemVo extends ERPManagerItem {
    private String itemBaseName;//itemBase 名
    
    private String managerName;//供应商名
    
    private String mdseId;//条形码
    
    private String spec;
    
    private String pkg;
    
    private String mdseIdX;
	private String name;
	private String picUrl;
	private String yiJiName;
	private String erJiName;
	private String managerId;
    

	public String getItemBaseName() {
		return itemBaseName;
	}

	public void setItemBaseName(String itemBaseName) {
		this.itemBaseName = itemBaseName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getMdseId() {
		return mdseId;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getMdseIdX() {
		return mdseIdX;
	}

	public void setMdseIdX(String mdseIdX) {
		this.mdseIdX = mdseIdX;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getYiJiName() {
		return yiJiName;
	}

	public void setYiJiName(String yiJiName) {
		this.yiJiName = yiJiName;
	}

	public String getErJiName() {
		return erJiName;
	}

	public void setErJiName(String erJiName) {
		this.erJiName = erJiName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
    
    
}
