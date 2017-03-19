/**   
* @Title: MaWalletLogRo.java 
* @Package com.corner.scms.beans.ro.fac 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年3月8日 下午3:59:14 
* @version V1.0   
*/

package com.corner.scms.beans.ro.ma;

import java.util.Date;

import com.corner.core.beans.ro.AmazeUIListRo;

/** 
* @ClassName: MaWalletLogRo 
* @Description:经销商交易记录查询类
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年3月8日 下午3:59:14 
*  
*/

public class MaWalletLogRo extends AmazeUIListRo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1756705268524572540L;
	private String maId;
	private Date startDate;
	private Date endDate;
	private String orderId;
	/**
	 * 1 - 今天	、	2 - 本月
	 */
	private int findType;
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
	
	public int getFindType() {
		return findType;
	}

	public void setFindType(int findType) {
		this.findType = findType;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

	public String getMaId() {
		return maId;
	}

	public void setMaId(String maId) {
		this.maId = maId;
	}
}
