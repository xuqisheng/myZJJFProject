/**   
* @Title: ERPPurchaseStockInfoVo.java 
* @Package com.corner.kefu.beans.vo.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月20日 下午6:54:56 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.erp;

import java.math.BigDecimal;
import java.util.List;

import com.corner.core.beans.ERPPurchaseStockInfo;

/** 
* @ClassName: ERPPurchaseStockInfoVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月20日 下午6:54:56 
*  
*/

public class ERPPurchaseStockInfoVo extends ERPPurchaseStockInfo {
	private Integer totalNum;
	
    private List<ERPPurchaseStockDetailVo> purchaseStockDetailVos; 
    
    @SuppressWarnings("unused")
	private BigDecimal totalPrice;
    
    private Integer totalProductNum = 0;
    
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public List<ERPPurchaseStockDetailVo> getPurchaseStockDetailVos() {
		return purchaseStockDetailVos;
	}

	public void setPurchaseStockDetailVos(List<ERPPurchaseStockDetailVo> purchaseStockDetailVos) {
		this.purchaseStockDetailVos = purchaseStockDetailVos;
	}

	public BigDecimal getTotalPrice() {
		BigDecimal price = new BigDecimal("0");
		if(purchaseStockDetailVos!=null&&purchaseStockDetailVos.size()!=0){
			for (ERPPurchaseStockDetailVo erpPurchaseStockDetailVo : purchaseStockDetailVos) {
				price = price.add(erpPurchaseStockDetailVo.getTotalPrice());
			}
		}
		return price;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalProductNum() {
		if(purchaseStockDetailVos!=null&&purchaseStockDetailVos.size()!=0){
			for (ERPPurchaseStockDetailVo erpPurchaseStockDetailVo : purchaseStockDetailVos) {
				totalProductNum+=erpPurchaseStockDetailVo.getOperateStock().intValue();
			}
		}
		return totalProductNum;
	}

	public void setTotalProductNum(Integer totalProductNum) {
		this.totalProductNum = totalProductNum;
	}


}
