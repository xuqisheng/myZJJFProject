/**   
 * @Title: SpWithDrawRo.java 
 * @Package com.corner.scms.beans.ro 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 杨开泰  yangkaitai@izjjf.cn   
 * @date 2015年12月7日 上午10:08:04 
 * @version V1.0   
 */
package com.corner.scms.beans.ro;


import com.corner.core.beans.ro.AmazeUIListRo;

import java.util.Date;

/**
 * @ClassName: SpWithDrawRo
 * @Description: 提现查询类
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2015年12月7日 上午10:08:04
 */
public class FinWalletLogRo extends AmazeUIListRo {
	private String walletId;
	private String orderId;
	private Date startTime;
	private Date endTime;
	private String thisMonth;
	private String thisDay;



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

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
