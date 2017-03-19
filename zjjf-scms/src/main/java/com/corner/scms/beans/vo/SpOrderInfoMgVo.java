/**   
* @Title: SpOrderInfoMgVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月9日 上午11:29:15 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import java.io.Serializable;

import com.corner.core.beans.SpOrderInfo;
import com.corner.core.enums.PayMethod;

/** 
* @ClassName: SpOrderInfoMgVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月9日 上午11:29:15 
*  
*/

public class SpOrderInfoMgVo extends SpOrderInfo implements Serializable {

	private static final long serialVersionUID = 4883506878881062559L;


	private String ziOrderId;//子订单orderId
    
	
	private SpOrderDetailMgVo detailMgVo;
	
	@SuppressWarnings("unused")
	private String payStr;
	
	

	public SpOrderDetailMgVo getDetailMgVo() {
		return detailMgVo;
	}

	public void setDetailMgVo(SpOrderDetailMgVo detailMgVo) {
		this.detailMgVo = detailMgVo;
	}

	public String getZiOrderId() {
		return ziOrderId;
	}

	public void setZiOrderId(String ziOrderId) {
		this.ziOrderId = ziOrderId;
	}

	public String getPayStr() {
		return PayMethod.getName(super.getSupportmetho());
	}

	public void setPayStr(String payStr) {
		this.payStr = payStr;
	}
}
