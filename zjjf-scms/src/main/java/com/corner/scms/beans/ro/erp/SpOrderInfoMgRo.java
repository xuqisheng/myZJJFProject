/**   
* @Title: SpOrderInfoMgRo.java 
* @Package com.corner.scms.beans.ro.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月8日 下午4:27:51 
* @version V1.0   
*/

package com.corner.scms.beans.ro.erp;

import com.corner.core.beans.SpOrderInfo;
import com.corner.scms.config.CommonPageConfig;

/** 
* @ClassName: SpOrderInfoMgRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月8日 下午4:27:51 
*  
*/

public class SpOrderInfoMgRo extends SpOrderInfo {

	private Integer pageIndex = CommonPageConfig.common_kkPage_index;
	
	private Integer pageSize = CommonPageConfig.common_kkPage_size;
	
	private String keyStr;
	
	private String ziOrderId;

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
		return keyStr;
	}

	public void setKeyStr(String keyStr) {
		this.keyStr = keyStr;
	}

	public String getZiOrderId() {
		return ziOrderId;
	}

	public void setZiOrderId(String ziOrderId) {
		this.ziOrderId = ziOrderId;
	}
	
	
}
