package com.zjjf.analysis.beans.erp.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ErpManagerOrderDetailVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String managerId;

	private String pid;

	private String itemId;

	private Integer itemBaseId;

	private String orderId;

	private String type;

	private String ManagerName;

	private String managerCode;

	private String managerStatus;

	private String statusName;

	private String cooperWarehouse;

	private String whName;

	private String whId;

	private String cooperation;

	private String barCode;

	private String mdseId;

	private String itemCode;

	private String itemName;

	private String spec;

	private String pkg;

	private Integer operateStock = 0;

	private Integer operateQuantity = 0;

	private String parentOrderId;

	private String wh1Name;

	private String wh2Name;

	private String wh3Name;

	private String addTime;

	private String addUser;

	private String gaveTime;

	private BigDecimal treasuryPrices = new BigDecimal(0);

	private BigDecimal areaPrice = new BigDecimal(0);

	private BigDecimal discountAreaPrice = new BigDecimal(0);

	private BigDecimal treasuryTotalPrices = new BigDecimal(0);

	private BigDecimal avgAreaPrice = new BigDecimal(0);

	private BigDecimal minAreaPrice = new BigDecimal(0);

	private String salePrice = "0";

	private String grossProfit = "0";

	private Double grossProfits = new Double(0);

	private String grossRate = "0";

	private String itemPrice;

	private String itemQuantity;

	private String supplierId;

	private String supplierName;

	private String taxRate = "0";

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	private List<String> orderIdList = new ArrayList<String>();

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getManagerName() {
		return ManagerName;
	}

	public void setManagerName(String managerName) {
		ManagerName = managerName;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getMdseId() {
		return mdseId;
	}

	public void setMdseId(String mdseId) {
		this.mdseId = mdseId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getOperateStock() {
		return operateStock;
	}

	public void setOperateStock(Integer operateStock) {
		this.operateStock = operateStock;
	}

	public Integer getOperateQuantity() {
		return operateQuantity;
	}

	public void setOperateQuantity(Integer operateQuantity) {
		this.operateQuantity = operateQuantity;
	}

	public String getParentOrderId() {
		return parentOrderId;
	}

	public void setParentOrderId(String parentOrderId) {
		this.parentOrderId = parentOrderId;
	}

	public String getWh1Name() {
		return wh1Name;
	}

	public void setWh1Name(String wh1Name) {
		this.wh1Name = wh1Name;
	}

	public String getWh2Name() {
		return wh2Name;
	}

	public void setWh2Name(String wh2Name) {
		this.wh2Name = wh2Name;
	}

	public String getWh3Name() {
		return wh3Name;
	}

	public void setWh3Name(String wh3Name) {
		this.wh3Name = wh3Name;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddUser() {
		return addUser;
	}

	public void setAddUser(String addUser) {
		this.addUser = addUser;
	}

	public String getGaveTime() {
		return gaveTime;
	}

	public void setGaveTime(String gaveTime) {
		this.gaveTime = gaveTime;
	}

	public BigDecimal getTreasuryPrices() {
		return treasuryPrices;
	}

	public void setTreasuryPrices(BigDecimal treasuryPrices) {
		this.treasuryPrices = treasuryPrices;
	}

	public BigDecimal getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(BigDecimal areaPrice) {
		this.areaPrice = areaPrice;
	}

	public BigDecimal getDiscountAreaPrice() {
		return discountAreaPrice;
	}

	public void setDiscountAreaPrice(BigDecimal discountAreaPrice) {
		this.discountAreaPrice = discountAreaPrice;
	}

	public BigDecimal getTreasuryTotalPrices() {
		return treasuryTotalPrices;
	}

	public void setTreasuryTotalPrices(BigDecimal treasuryTotalPrices) {
		this.treasuryTotalPrices = treasuryTotalPrices;
	}

	public BigDecimal getAvgAreaPrice() {
		return avgAreaPrice;
	}

	public void setAvgAreaPrice(BigDecimal avgAreaPrice) {
		this.avgAreaPrice = avgAreaPrice;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(String grossProfit) {
		this.grossProfit = grossProfit;
	}

	public String getGrossRate() {
		return grossRate;
	}

	public void setGrossRate(String grossRate) {
		this.grossRate = grossRate;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public List<String> getOrderIdList() {
		return orderIdList;
	}

	public void setOrderIdList(List<String> orderIdList) {
		this.orderIdList = orderIdList;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerStatus() {
		return managerStatus;
	}

	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}

	public String getCooperWarehouse() {
		return cooperWarehouse;
	}

	public void setCooperWarehouse(String cooperWarehouse) {
		this.cooperWarehouse = cooperWarehouse;
	}

	public String getCooperation() {
		return cooperation;
	}

	public void setCooperation(String cooperation) {
		this.cooperation = cooperation;
	}

	public BigDecimal getMinAreaPrice() {
		return minAreaPrice;
	}

	public void setMinAreaPrice(BigDecimal minAreaPrice) {
		this.minAreaPrice = minAreaPrice;
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public Double getGrossProfits() {
		return grossProfits;
	}

	public void setGrossProfits(Double grossProfits) {
		this.grossProfits = grossProfits;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

}