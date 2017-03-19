/**   
* @Title: ERPMarketStockDetailVo.java 
* @Package com.corner.scms.beans.vo.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月8日 下午2:12:49 
* @version V1.0   
*/

package com.corner.scms.beans.vo.erp;

import com.corner.core.beans.ERPMarketStockDetail;

/** 
* @ClassName: ERPMarketStockDetailVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月8日 下午2:12:49 
*  
*/

public class ERPMarketStockDetailVo extends ERPMarketStockDetail {
	
	private short spDetailOutStockNum;//SpOrderDetail的outStockNum

	public short getSpDetailOutStockNum() {
		return spDetailOutStockNum;
	}

	public void setSpDetailOutStockNum(short spDetailOutStockNum) {
		this.spDetailOutStockNum = spDetailOutStockNum;
	}

}
