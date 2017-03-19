/**   
* @Title: SpVoucherTempRo.java 
* @Package com.corner.mobile.common.beans.ro 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月6日 上午11:24:46 
* @version V1.0   
*/

package com.corner.kefu.beans.ro.sp;

import java.util.Date;

import com.corner.core.beans.SpVoucherTemp;
import com.corner.kefu.config.CommonPageConfig;

/** 
* @ClassName: SpVoucherTempRo 
* @Description:优惠劵模板查询类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年1月6日 上午11:24:46 
*  
*/

public class SpVoucherTempRo extends SpVoucherTemp {
	private Integer pageIndex = CommonPageConfig.asyncPageIndex;
	private Integer pageSize = CommonPageConfig.asyncPageSize;
	private String searchKey;
	private Date startDate;
	private Date endDate;
	private String useMoneyStr;

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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getUseMoneyStr() {
		return useMoneyStr;
	}

	public void setUseMoneyStr(String useMoneyStr) {
		this.useMoneyStr = useMoneyStr;
	}
}
