package com.zjjf.analysis.beans.analysis.corner;

import java.io.Serializable;
import java.math.BigDecimal;

public class ErpPurchaseStockDetailVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pid;

	private String orderId;

	private Integer itemBaseId;

	private String supplierId;

	private String settleStatusName;

	private String managerName;

	private String managerId;

	private String whId;

	private String barCode;

	private String mdseId;

	private String itemCode;

	private String name;

	private String spec;

	private Short quantity = 0;

	private Integer operateQuantity = 0;

	private Integer operateStock = 0;

	private String parentOrderId;

	private String wh1Name;

	private String wh2Name;

	private String wh3Name;

	private String addTime;

	private String addUser;

	private String gaveTime;

	private BigDecimal price = new BigDecimal(0);

	private String areaPrice = "0";

	private String discountAreaPrice = "0";

	private BigDecimal totalPrice = new BigDecimal(0);

	private String avgAreaPrice = "0";

	private String salePrice = "0";

	private String grossProfit = "0";

	private String grossRate = "0";

	private String productionTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSettleStatusName() {
		return settleStatusName;
	}

	public void setSettleStatusName(String settleStatusName) {
		this.settleStatusName = settleStatusName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Short getQuantity() {
		return quantity;
	}

	public void setQuantity(Short quantity) {
		this.quantity = quantity;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getAreaPrice() {
		return areaPrice;
	}

	public void setAreaPrice(String areaPrice) {
		this.areaPrice = areaPrice;
	}

	public String getDiscountAreaPrice() {
		return discountAreaPrice;
	}

	public void setDiscountAreaPrice(String discountAreaPrice) {
		this.discountAreaPrice = discountAreaPrice;
	}

	public String getAvgAreaPrice() {
		return avgAreaPrice;
	}

	public void setAvgAreaPrice(String avgAreaPrice) {
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getProductionTime() {
		return productionTime;
	}

	public void setProductionTime(String productionTime) {
		this.productionTime = productionTime;
	}

	public Integer getOperateStock() {
		return operateStock;
	}

	public void setOperateStock(Integer operateStock) {
		this.operateStock = operateStock;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

}