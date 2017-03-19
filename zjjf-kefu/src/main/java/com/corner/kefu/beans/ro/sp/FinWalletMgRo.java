/**   
* @Title: FinWalletMgRo.java 
* @Package com.corner.kefu.beans.ro.sp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年7月21日 下午2:36:59 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.sp;

import java.util.Date;

import com.corner.core.beans.FinWallet;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: FinWalletMgRo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年7月21日 下午2:36:59 
*  
*/

public class FinWalletMgRo extends FinWallet {

	private Integer pageIndex = CommonPageConfig.commonPageIndex;

	private Integer pageSize = CommonPageConfig.commonPageSize;

	private Date startTime;

	private Date endTime;

	public Integer getPageIndex() {
		return (pageIndex - 1) * pageSize;
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
