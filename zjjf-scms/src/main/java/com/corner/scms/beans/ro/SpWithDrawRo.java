/**   
 * @Title: SpWithDrawRo.java 
 * @Package com.corner.scms.beans.ro 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 杨开泰  yangkaitai@izjjf.cn   
 * @date 2015年12月7日 上午10:08:04 
 * @version V1.0   
 */
package com.corner.scms.beans.ro;


import java.util.Date;

import com.corner.core.beans.SpWithDraw;
import com.corner.core.beans.ro.AmazeUIListRo;
import com.corner.scms.config.CommonPageConfig;

/**
 * @ClassName: SpWithDrawRo
 * @Description: 提现查询类
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月7日 上午10:08:04
 */
public class SpWithDrawRo extends AmazeUIListRo {
	private Date startDate;
	private Date endDate;
	private String thisMonth;
	private String thisDay;
	private String supplierId;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getThisMonth() {
		return thisMonth;
	}

	public void setThisMonth(String thisMonth) {
		this.thisMonth = thisMonth;
	}

	public String getThisDay() {
		return thisDay;
	}

	public void setThisDay(String thisDay) {
		this.thisDay = thisDay;
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
}
