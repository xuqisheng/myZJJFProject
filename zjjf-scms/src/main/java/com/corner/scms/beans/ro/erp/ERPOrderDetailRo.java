/**   
 * @Title: PlantItemRo.java 
 * @Package com.corner.scms.beans.ro 
 * @Description:
 * @author 杨开泰  yangkaitai@izjjf.cn   
 * @date 2015年12月8日 下午5:49:14 
 * @version V1.0   
 */
package com.corner.scms.beans.ro.erp;

import com.corner.core.beans.ERPManagerOrderDetail;
import com.corner.core.beans.ro.AmazeUIListRo;

import java.math.BigDecimal;
import java.util.Date;

public class ERPOrderDetailRo extends AmazeUIListRo {
	private String whId;
	private Byte type;
	private String[] itemIds;
	private String[] itemCodes;
	private Integer[] itemBaseIds;
	private Short[] quantitys;
	private BigDecimal[] areaPrices;
	private String[] managerIds;
	private Date[] productionDates;
	private String[] producingAreas;
	private String[] ids;
	private String[] warehouseIdLevel3s;
	private Byte[] stockTypes;
	private String supplierId;
	private String supplierName;
	private String managerId;
	private String orderId;
	private Byte status;
	private String remark;
	private Date gaveTime;

	public String[] getItemCodes() {
		return itemCodes;
	}

	public void setItemCodes(String[] itemCodes) {
		this.itemCodes = itemCodes;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Byte[] getStockTypes() {
		return stockTypes;
	}

	public void setStockTypes(Byte[] stockTypes) {
		this.stockTypes = stockTypes;
	}

	public String[] getWarehouseIdLevel3s() {
		return warehouseIdLevel3s;
	}

	public void setWarehouseIdLevel3s(String[] warehouseIdLevel3s) {
		this.warehouseIdLevel3s = warehouseIdLevel3s;
	}

	public Date getGaveTime() {
		return gaveTime;
	}

	public void setGaveTime(Date gaveTime) {
		this.gaveTime = gaveTime;
	}

	public String[] getProducingAreas() {
		return producingAreas;
	}

	public void setProducingAreas(String[] producingAreas) {
		this.producingAreas = producingAreas;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Date[] getProductionDates() {
		return productionDates;
	}

	public void setProductionDates(Date[] productionDates) {
		this.productionDates = productionDates;
	}

	public Integer[] getItemBaseIds() {
		return itemBaseIds;
	}

	public void setItemBaseIds(Integer[] itemBaseIds) {
		this.itemBaseIds = itemBaseIds;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String[] getManagerIds() {
		return managerIds;
	}

	public void setManagerIds(String[] managerIds) {
		this.managerIds = managerIds;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String[] getItemIds() {
		return itemIds;
	}

	public void setItemIds(String[] itemIds) {
		this.itemIds = itemIds;
	}

	public Short[] getQuantitys() {
		return quantitys;
	}

	public void setQuantitys(Short[] quantitys) {
		this.quantitys = quantitys;
	}

	public BigDecimal[] getAreaPrices() {
		return areaPrices;
	}

	public void setAreaPrices(BigDecimal[] areaPrices) {
		this.areaPrices = areaPrices;
	}
}
