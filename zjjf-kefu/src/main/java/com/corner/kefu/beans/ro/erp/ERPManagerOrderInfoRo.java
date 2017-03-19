/**   
* @Title: ERPManagerOrderInfoRo.java 
* @Package com.corner.kefu.beans.ro.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月5日 下午3:34:09 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.erp;

import java.util.Date;

import com.corner.core.beans.ERPManagerOrderInfo;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: ERPManagerOrderInfoRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月5日 下午3:34:09 
*  
*/

public class ERPManagerOrderInfoRo extends ERPManagerOrderInfo {

	private Integer pageIndex = CommonPageConfig.commonPageIndex;
	
	private Integer pageSize = CommonPageConfig.commonPageSize;
	
	private Date startTime;
	
	private Date endTime;
	

	public Integer getPageIndex() {
		return (pageIndex-1<=0?0:(pageIndex-1))*pageSize;
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
