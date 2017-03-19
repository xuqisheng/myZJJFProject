/**   
* @Title: MaWalletLogRo.java 
* @Package com.corner.scms.beans.ro.fac 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月8日 下午3:59:14 
* @version V1.0   
*/

package com.corner.scms.beans.ro.fac;

import java.util.Date;

import com.corner.core.beans.MaWalletLog;
import com.corner.scms.config.CommonPageConfig;

/** 
* @ClassName: MaWalletLogRo 
* @Description:经销商交易记录查询类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月8日 下午3:59:14 
*  
*/

public class MaWalletLogRo extends MaWalletLog {
	private int pageIndex = CommonPageConfig.scms_spwallet_pageIndex;
	private int pageSize = CommonPageConfig.scms_spwallet_pageSize;
	private Date startTime;
	private Date endTime;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
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
