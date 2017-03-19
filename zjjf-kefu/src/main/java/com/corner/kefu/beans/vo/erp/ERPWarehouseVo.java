/**   
* @Title: ERPWarehouseVo.java 
* @Package com.corner.kefu.beans.vo.erp 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年9月16日 下午3:34:08 
* @version V1.0   
*/

package com.corner.kefu.beans.vo.erp;

import com.corner.core.beans.ERPWarehouse;

/** 
* @ClassName: ERPWarehouseVo 
* @Description:
* @author 杨开泰  yangkaitai@izjjf.cn
* @date 2016年9月16日 下午3:34:08 
*  
*/

public class ERPWarehouseVo extends ERPWarehouse {
	private String supplierName;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
}
