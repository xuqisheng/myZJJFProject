/**   
* @Title: ERPWarehouseRo.java 
* @Package com.corner.kefu.beans.ro.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月5日 上午9:26:53 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.erp;

import com.corner.core.beans.ERPWarehouse;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: ERPWarehouseRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月5日 上午9:26:53 
*  
*/

public class ERPWarehouseRo extends ERPWarehouse {

	private Integer pageIndex = CommonPageConfig.asyncPageIndex;
	
	private Integer pageSize = CommonPageConfig.asyncPageSize;
	
	private String keyStr;

	public Integer getPageIndex() {
		return pageIndex * pageSize;
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
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}
}
