/**   
* @Title: ERPPurchaseStockInfoRo.java 
* @Package com.corner.kefu.beans.ro.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月20日 下午6:52:50 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.erp;

import java.util.Date;

import com.corner.core.beans.ERPPurchaseStockInfo;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: ERPPurchaseStockInfoRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月20日 下午6:52:50 
*  
*/

public class ERPPurchaseStockInfoRo extends ERPPurchaseStockInfo {

	private Integer pageIndex = CommonPageConfig.commonPageIndex;
	
	private Integer pageSize = CommonPageConfig.commonPageSize;

	private String keyStr;
	
	private Date startTime;
	
	private Date endTime;
	
	public Integer getPageIndex() {
		return (pageIndex-1)*pageSize;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
