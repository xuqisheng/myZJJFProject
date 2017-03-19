package com.zjjf.analysis.beans.analysis.orders;

import java.math.BigDecimal;
import java.util.Date;

public class ErpPurchaseDaily {
    private Integer id;

    private String purchaseId;

    private Byte type;

    private String managerId;

    private String managerName;

    private String barCode;

    private String boxCode;

    private String itemName;

    private String itemSpec;

    private Integer operateQuantity;

    private Integer needQuantity;

    private String inStorageNo;

    private String inStorageUser;

    private Date inStorageTime;

    private String warehouseId;

    private String warehouseName;

    private String wh3Name;

    private Date addTime;

    private String addUser;

    private Date gaveTime;

    private Date productTime;

    private String productArea;

    private BigDecimal itemPrice;

    private BigDecimal realItemPrice;

    private BigDecimal discountItemPrice;

    private BigDecimal minItemPrice;

    private BigDecimal totalInStoragePrice;

    private BigDecimal avgSlaePrice;

    private BigDecimal profit;

    private BigDecimal profitRate;

    private Integer dayTime;

    private Integer createTime;

    private Date updateTime;

    private String itemId;

    private Integer itemBaseId;

    private String supplierId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(String purchaseId) {
        this.purchaseId = purchaseId == null ? null : purchaseId.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode == null ? null : boxCode.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec == null ? null : itemSpec.trim();
    }

    public Integer getOperateQuantity() {
        return operateQuantity;
    }

    public void setOperateQuantity(Integer operateQuantity) {
        this.operateQuantity = operateQuantity;
    }

    public Integer getNeedQuantity() {
        return needQuantity;
    }

    public void setNeedQuantity(Integer needQuantity) {
        this.needQuantity = needQuantity;
    }

    public String getInStorageNo() {
        return inStorageNo;
    }

    public void setInStorageNo(String inStorageNo) {
        this.inStorageNo = inStorageNo == null ? null : inStorageNo.trim();
    }

    public String getInStorageUser() {
        return inStorageUser;
    }

    public void setInStorageUser(String inStorageUser) {
        this.inStorageUser = inStorageUser == null ? null : inStorageUser.trim();
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId == null ? null : warehouseId.trim();
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName == null ? null : warehouseName.trim();
    }

    public String getWh3Name() {
        return wh3Name;
    }

    public void setWh3Name(String wh3Name) {
        this.wh3Name = wh3Name == null ? null : wh3Name.trim();
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser == null ? null : addUser.trim();
    }

    public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getGaveTime() {
		return gaveTime;
	}

	public void setGaveTime(Date gaveTime) {
		this.gaveTime = gaveTime;
	}

	
    public Date getInStorageTime() {
		return inStorageTime;
	}

	public void setInStorageTime(Date inStorageTime) {
		this.inStorageTime = inStorageTime;
	}

	public Date getProductTime() {
		return productTime;
	}

	public void setProductTime(Date productTime) {
		this.productTime = productTime;
	}

	public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea == null ? null : productArea.trim();
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getRealItemPrice() {
        return realItemPrice;
    }

    public void setRealItemPrice(BigDecimal realItemPrice) {
        this.realItemPrice = realItemPrice;
    }

    public BigDecimal getDiscountItemPrice() {
        return discountItemPrice;
    }

    public void setDiscountItemPrice(BigDecimal discountItemPrice) {
        this.discountItemPrice = discountItemPrice;
    }

    public BigDecimal getMinItemPrice() {
        return minItemPrice;
    }

    public void setMinItemPrice(BigDecimal minItemPrice) {
        this.minItemPrice = minItemPrice;
    }

    public BigDecimal getTotalInStoragePrice() {
        return totalInStoragePrice;
    }

    public void setTotalInStoragePrice(BigDecimal totalInStoragePrice) {
        this.totalInStoragePrice = totalInStoragePrice;
    }

    public BigDecimal getAvgSlaePrice() {
        return avgSlaePrice;
    }

    public void setAvgSlaePrice(BigDecimal avgSlaePrice) {
        this.avgSlaePrice = avgSlaePrice;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public Integer getDayTime() {
        return dayTime;
    }

    public void setDayTime(Integer dayTime) {
        this.dayTime = dayTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
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
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }
}