/**   
* @Title: ERPMarketStockInfoRo.java 
* @Package com.corner.scms.beans.ro.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月8日 下午2:12:07 
* @version V1.0   
*/

package com.corner.scms.beans.ro.erp;

import java.util.Date;

import com.corner.core.beans.ERPMarketStockInfo;
import com.corner.scms.config.CommonPageConfig;

/** 
* @ClassName: ERPMarketStockInfoRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月8日 下午2:12:07 
*  
*/

public class ERPMarketStockInfoRo extends ERPMarketStockInfo {
	
	private Integer pageIndex = CommonPageConfig.commonPageIndex;
	
	private Integer pageSize = CommonPageConfig.commonPageSize;
	
	private String keyStr;
	
	private Byte stockStatus;
	
	private Date startTime;
	
	private Date endTime;
	

	public Integer getPageIndex() {
		return (pageIndex-1<0?0:pageIndex-1)*pageSize;
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

	public Byte getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(Byte stockStatus) {
		this.stockStatus = stockStatus;
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
