package com.zjjf.analysis.beans.analysis.items;

import java.math.BigDecimal;
import java.util.Date;

public class ERPManagerItemDaily {
    private Integer id;

    private String managerId;

    private String managerName;

    private String address;

    private String tel;

    private String fax;

    private String branderName;

    private String job;

    private String mobile;

    private String email;

    private String whAddress;

    private String whBranderName;

    private String whMobile;

    private Byte cleaningDayStatus;

    private String bankName;

    private String bankUserName;

    private String bankNum;

    private String taxNumber;

    private Byte status;

    private String cooperWarehouse;

    private Byte cooperation;

    private String itemCode;

    private String barCode;

    private String boxCode;

    private String itemName;

    private String itemSpec;

    private String itemPkg;

    private BigDecimal areaPrice;

    private BigDecimal taxRate;

    private BigDecimal avgAreaPrice;

    private BigDecimal discountAreaPrice;

    private BigDecimal minAreaPrice;

    private BigDecimal salePrice;

    private BigDecimal grossProfit;

    private BigDecimal grossRate;

    private Integer dayTime;

    private Date updataTime;

    private Integer createTime;
    
    private Date addTime;
    
    private String orderId;
    
    private String itemId;
    
    private Integer itemBaseId;
    
    private String pid;
    
    private String supplierId;
    
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getItemBaseId() {
		return itemBaseId;
	}

	public void setItemBaseId(Integer itemBaseId) {
		this.itemBaseId = itemBaseId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getBranderName() {
        return branderName;
    }

    public void setBranderName(String branderName) {
        this.branderName = branderName == null ? null : branderName.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getWhAddress() {
        return whAddress;
    }

    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress == null ? null : whAddress.trim();
    }

    public String getWhBranderName() {
        return whBranderName;
    }

    public void setWhBranderName(String whBranderName) {
        this.whBranderName = whBranderName == null ? null : whBranderName.trim();
    }

    public String getWhMobile() {
        return whMobile;
    }

    public void setWhMobile(String whMobile) {
        this.whMobile = whMobile == null ? null : whMobile.trim();
    }

    public Byte getCleaningDayStatus() {
        return cleaningDayStatus;
    }

    public void setCleaningDayStatus(Byte cleaningDayStatus) {
        this.cleaningDayStatus = cleaningDayStatus;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName == null ? null : bankUserName.trim();
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum == null ? null : bankNum.trim();
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber == null ? null : taxNumber.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCooperWarehouse() {
        return cooperWarehouse;
    }

    public void setCooperWarehouse(String cooperWarehouse) {
        this.cooperWarehouse = cooperWarehouse == null ? null : cooperWarehouse.trim();
    }

    public Byte getCooperation() {
        return cooperation;
    }

    public void setCooperation(Byte cooperation) {
        this.cooperation = cooperation;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
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

    public String getItemPkg() {
        return itemPkg;
    }

    public void setItemPkg(String itemPkg) {
        this.itemPkg = itemPkg == null ? null : itemPkg.trim();
    }

    public BigDecimal getAreaPrice() {
        return areaPrice;
    }

    public void setAreaPrice(BigDecimal areaPrice) {
        this.areaPrice = areaPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getAvgAreaPrice() {
        return avgAreaPrice;
    }

    public void setAvgAreaPrice(BigDecimal avgAreaPrice) {
        this.avgAreaPrice = avgAreaPrice;
    }

    public BigDecimal getDiscountAreaPrice() {
        return discountAreaPrice;
    }

    public void setDiscountAreaPrice(BigDecimal discountAreaPrice) {
        this.discountAreaPrice = discountAreaPrice;
    }

    public BigDecimal getMinAreaPrice() {
        return minAreaPrice;
    }

    public void setMinAreaPrice(BigDecimal minAreaPrice) {
        this.minAreaPrice = minAreaPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
    }

    public BigDecimal getGrossRate() {
        return grossRate;
    }

    public void setGrossRate(BigDecimal grossRate) {
        this.grossRate = grossRate;
    }

    public Integer getDayTime() {
        return dayTime;
    }

    public void setDayTime(Integer dayTime) {
        this.dayTime = dayTime;
    }

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
}