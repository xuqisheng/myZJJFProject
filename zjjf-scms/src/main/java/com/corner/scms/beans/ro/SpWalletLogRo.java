/**   
 * @Title: SpWalletLogRo.java 
 * @Package com.corner.scms.beans.ro 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 杨开泰  yangkaitai@izjjf.cn   
 * @date 2015年12月7日 上午10:09:50 
 * @version V1.0   
 */
package com.corner.scms.beans.ro;


import java.util.Date;

import com.corner.core.beans.SpWalletLog;
import com.corner.scms.config.CommonPageConfig;

/**
 * @ClassName: SpWalletLogRo
 * @Description: 交易明细查询类
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月7日 上午10:09:50
 */
public class SpWalletLogRo extends SpWalletLog {
	private int pageIndex = CommonPageConfig.scms_spwallet_pageIndex;
	private int pageSize = CommonPageConfig.scms_spwallet_pageSize;
	private Date startTime;
	private Date endTime;

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

	public int getPageIndex() {
		return pageIndex*pageSize;
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
}
