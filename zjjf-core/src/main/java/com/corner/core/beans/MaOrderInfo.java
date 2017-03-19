package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

import com.corner.core.utils.StringUtil;

public class MaOrderInfo {
    private String id = StringUtil.getUUID();

    private String orderId;

    private Date addTime;

    private BigDecimal orderPrice = new BigDecimal("0");

    private BigDecimal goodsPrice= new BigDecimal("0");

    private BigDecimal markePrice= new BigDecimal("0");

    private BigDecimal outOfPrice= new BigDecimal("0");

    private String kfId;

    private String kfName;

    private String kfnote;

    private Byte kfStatus;

    private Date kfSubmitTime;

    private Integer groupId;

    private String groupName;

    private String consignee;

    private String mobile;

    private String userTel;

    private String address;

    private Byte status;

    private Date supportTime;

    private Byte ordertype;

    private String managerId;

    private String managerTel;

    private String managerName;

    private Byte managerStatus;

    private Date managerCheckTime;

    private Date managerPrintTime;

    private Byte managerPrintNum;

    private Date fistTime;

    private Date endTime;

    private BigDecimal zmaoli;

    private BigDecimal zfee;

    private String warehouseId;

    private String warehouseName;

    private Date warehouseTime;

    private Byte warehouseStatus;

    private String acId;

    private Integer acStatus;

    private String acRemark;

    private Date acCheckTime;

    private Date acSettleTime;

    private Date acPayTime;

    private Boolean isDelete;

    private Boolean whPayStatus;

    private Byte whPayMetho;

    private Date whPayTime;

    private Long whQbPayId;

    private BigDecimal whQbPayNum;

    private Long whQbGetId;

    private BigDecimal whQbGetNum;

    private String whTradeNo;

    private String whAccountId;

    private String whAcRemark;

    private BigDecimal whFreight;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getMarkePrice() {
        return markePrice;
    }

    public void setMarkePrice(BigDecimal markePrice) {
        this.markePrice = markePrice;
    }

    public BigDecimal getOutOfPrice() {
        return outOfPrice;
    }

    public void setOutOfPrice(BigDecimal outOfPrice) {
        this.outOfPrice = outOfPrice;
    }

    public String getKfId() {
        return kfId;
    }

    public void setKfId(String kfId) {
        this.kfId = kfId == null ? null : kfId.trim();
    }

    public String getKfName() {
        return kfName;
    }

    public void setKfName(String kfName) {
        this.kfName = kfName == null ? null : kfName.trim();
    }

    public String getKfnote() {
        return kfnote;
    }

    public void setKfnote(String kfnote) {
        this.kfnote = kfnote == null ? null : kfnote.trim();
    }

    public Byte getKfStatus() {
        return kfStatus;
    }

    public void setKfStatus(Byte kfStatus) {
        this.kfStatus = kfStatus;
    }

    public Date getKfSubmitTime() {
        return kfSubmitTime;
    }

    public void setKfSubmitTime(Date kfSubmitTime) {
        this.kfSubmitTime = kfSubmitTime;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getSupportTime() {
        return supportTime;
    }

    public void setSupportTime(Date supportTime) {
        this.supportTime = supportTime;
    }

    public Byte getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(Byte ordertype) {
        this.ordertype = ordertype;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    public String getManagerTel() {
        return managerTel;
    }

    public void setManagerTel(String managerTel) {
        this.managerTel = managerTel == null ? null : managerTel.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public Byte getManagerStatus() {
        return managerStatus;
    }

    public void setManagerStatus(Byte managerStatus) {
        this.managerStatus = managerStatus;
    }

    public Date getManagerCheckTime() {
        return managerCheckTime;
    }

    public void setManagerCheckTime(Date managerCheckTime) {
        this.managerCheckTime = managerCheckTime;
    }

    public Date getManagerPrintTime() {
        return managerPrintTime;
    }

    public void setManagerPrintTime(Date managerPrintTime) {
        this.managerPrintTime = managerPrintTime;
    }

    public Byte getManagerPrintNum() {
        return managerPrintNum;
    }

    public void setManagerPrintNum(Byte managerPrintNum) {
        this.managerPrintNum = managerPrintNum;
    }

    public Date getFistTime() {
        return fistTime;
    }

    public void setFistTime(Date fistTime) {
        this.fistTime = fistTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getZmaoli() {
        return zmaoli;
    }

    public void setZmaoli(BigDecimal zmaoli) {
        this.zmaoli = zmaoli;
    }

    public BigDecimal getZfee() {
        return zfee;
    }

    public void setZfee(BigDecimal zfee) {
        this.zfee = zfee;
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

    public Date getWarehouseTime() {
        return warehouseTime;
    }

    public void setWarehouseTime(Date warehouseTime) {
        this.warehouseTime = warehouseTime;
    }

    public Byte getWarehouseStatus() {
        return warehouseStatus;
    }

    public void setWarehouseStatus(Byte warehouseStatus) {
        this.warehouseStatus = warehouseStatus;
    }

    public String getAcId() {
        return acId;
    }

    public void setAcId(String acId) {
        this.acId = acId == null ? null : acId.trim();
    }

    public Integer getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(Integer acStatus) {
        this.acStatus = acStatus;
    }

    public String getAcRemark() {
        return acRemark;
    }

    public void setAcRemark(String acRemark) {
        this.acRemark = acRemark == null ? null : acRemark.trim();
    }

    public Date getAcCheckTime() {
        return acCheckTime;
    }

    public void setAcCheckTime(Date acCheckTime) {
        this.acCheckTime = acCheckTime;
    }

    public Date getAcSettleTime() {
        return acSettleTime;
    }

    public void setAcSettleTime(Date acSettleTime) {
        this.acSettleTime = acSettleTime;
    }

    public Date getAcPayTime() {
        return acPayTime;
    }

    public void setAcPayTime(Date acPayTime) {
        this.acPayTime = acPayTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getWhPayStatus() {
        return whPayStatus;
    }

    public void setWhPayStatus(Boolean whPayStatus) {
        this.whPayStatus = whPayStatus;
    }

    public Byte getWhPayMetho() {
        return whPayMetho;
    }

    public void setWhPayMetho(Byte whPayMetho) {
        this.whPayMetho = whPayMetho;
    }

    public Date getWhPayTime() {
        return whPayTime;
    }

    public void setWhPayTime(Date whPayTime) {
        this.whPayTime = whPayTime;
    }

    public Long getWhQbPayId() {
        return whQbPayId;
    }

    public void setWhQbPayId(Long whQbPayId) {
        this.whQbPayId = whQbPayId;
    }

    public BigDecimal getWhQbPayNum() {
        return whQbPayNum;
    }

    public void setWhQbPayNum(BigDecimal whQbPayNum) {
        this.whQbPayNum = whQbPayNum;
    }

    public Long getWhQbGetId() {
        return whQbGetId;
    }

    public void setWhQbGetId(Long whQbGetId) {
        this.whQbGetId = whQbGetId;
    }

    public BigDecimal getWhQbGetNum() {
        return whQbGetNum;
    }

    public void setWhQbGetNum(BigDecimal whQbGetNum) {
        this.whQbGetNum = whQbGetNum;
    }

    public String getWhTradeNo() {
        return whTradeNo;
    }

    public void setWhTradeNo(String whTradeNo) {
        this.whTradeNo = whTradeNo == null ? null : whTradeNo.trim();
    }

    public String getWhAccountId() {
        return whAccountId;
    }

    public void setWhAccountId(String whAccountId) {
        this.whAccountId = whAccountId == null ? null : whAccountId.trim();
    }

    public String getWhAcRemark() {
        return whAcRemark;
    }

    public void setWhAcRemark(String whAcRemark) {
        this.whAcRemark = whAcRemark == null ? null : whAcRemark.trim();
    }

    public BigDecimal getWhFreight() {
        return whFreight;
    }

    public void setWhFreight(BigDecimal whFreight) {
        this.whFreight = whFreight;
    }
}