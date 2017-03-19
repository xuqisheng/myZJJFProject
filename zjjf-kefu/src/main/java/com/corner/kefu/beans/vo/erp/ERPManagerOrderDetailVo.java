/**   
* @Title: ERPManagerOrderDetailVo.java 
* @Package com.corner.kefu.beans.vo.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月6日 上午10:30:53 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.erp;

import java.math.BigDecimal;

import com.corner.core.beans.ERPManagerOrderDetail;

/** 
* @ClassName: ERPManagerOrderDetailVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月6日 上午10:30:53 
*  
*/

public class ERPManagerOrderDetailVo extends ERPManagerOrderDetail {
	
	private BigDecimal totalPrice;

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
