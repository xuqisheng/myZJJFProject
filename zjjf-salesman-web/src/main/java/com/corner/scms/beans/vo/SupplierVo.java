/**   
* @Title: SupplierVo.java 
* @Package com.corner.scms.beans.vo 
* @Description:
* @author 杨开泰 yangkaitai@izjjf.cn 
* @date 2016年1月26日 上午10:19:23 
* @version V1.0   
*/

package com.corner.scms.beans.vo;

import com.corner.core.beans.Supplier;

/**
 * @ClassName: SupplierVo
 * @Description:
 * @author 杨开泰 yangkaitai@izjjf.cn
 * @date 2016年1月26日 上午10:19:23
 * 
 */

public class SupplierVo extends Supplier {
	private String warehouseId;
	private String warehouseName;

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

}
