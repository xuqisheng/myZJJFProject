package com.corner.kefu.beans.ro.scms;

import java.util.ArrayList;
import java.util.List;

import com.corner.core.beans.ScOrderDetail;
import com.corner.core.beans.ScOrderInfo;
import com.corner.kefu.beans.vo.ScOrderDetailVo;

public class ScOrderInfoMgRo extends ScOrderInfo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1284697009164468103L;
	
	
	/** 订单开始时间 */
    private String getOrderTimeStart;
    /** 订单结束时间     */
    private String getOrderTimeEnd;
    private String itemBaseId;
    private int totQuantity;
	
    /**1.添加	, 0  删除    批发商**/
    private String addOrDel;
    

	public int getTotQuantity() {
		return totQuantity;
	}

	public void setTotQuantity(int totQuantity) {
		this.totQuantity = totQuantity;
	}

	private List<ScOrderDetailVo> scOrderDetails;
    
	public String getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(String itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public String getGetOrderTimeStart() {
		return getOrderTimeStart;
	}

	public void setGetOrderTimeStart(String getOrderTimeStart) {
		this.getOrderTimeStart = getOrderTimeStart;
	}

	public String getGetOrderTimeEnd() {
		return getOrderTimeEnd;
	}

	public void setGetOrderTimeEnd(String getOrderTimeEnd) {
		this.getOrderTimeEnd = getOrderTimeEnd;
	}

	public List<ScOrderDetailVo> getScOrderDetails() {
		return scOrderDetails;
	}

	public void setScOrderDetails(ScOrderDetailVo scOrderDetail) {
		if(this.scOrderDetails == null){
			this.scOrderDetails = new ArrayList<ScOrderDetailVo>();
		}
		this.scOrderDetails.add(scOrderDetail);
	}

	public String getAddOrDel() {
		return addOrDel;
	}

	public void setAddOrDel(String addOrDel) {
		this.addOrDel = addOrDel;
	}

	public void setScOrderDetails(List<ScOrderDetailVo> scOrderDetails) {
		this.scOrderDetails = scOrderDetails;
	}
}