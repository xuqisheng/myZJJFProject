package com.corner.core.beans;

import java.util.Date;

import com.corner.core.utils.StringUtil;

public class WhWDSheet {
    private String id = StringUtil.getUUID();

    private String sheetNum;

    private String sheetName;

    private String sheetRemark;

    private String sheetPath;

    private Date createTime;

    private String createUser;

    private String payBankNum;

    private String payBankName;

    private Date payTime;

    private Date fillTime;

    private String fillUserId;

    private String fillRemark;

    private Byte ordId;

    private Byte xtype;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSheetNum() {
        return sheetNum;
    }

    public void setSheetNum(String sheetNum) {
        this.sheetNum = sheetNum == null ? null : sheetNum.trim();
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName == null ? null : sheetName.trim();
    }

    public String getSheetRemark() {
        return sheetRemark;
    }

    public void setSheetRemark(String sheetRemark) {
        this.sheetRemark = sheetRemark == null ? null : sheetRemark.trim();
    }

    public String getSheetPath() {
        return sheetPath;
    }

    public void setSheetPath(String sheetPath) {
        this.sheetPath = sheetPath == null ? null : sheetPath.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getPayBankNum() {
        return payBankNum;
    }

    public void setPayBankNum(String payBankNum) {
        this.payBankNum = payBankNum == null ? null : payBankNum.trim();
    }

    public String getPayBankName() {
        return payBankName;
    }

    public void setPayBankName(String payBankName) {
        this.payBankName = payBankName == null ? null : payBankName.trim();
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getFillTime() {
        return fillTime;
    }

    public void setFillTime(Date fillTime) {
        this.fillTime = fillTime;
    }

    public String getFillUserId() {
        return fillUserId;
    }

    public void setFillUserId(String fillUserId) {
        this.fillUserId = fillUserId == null ? null : fillUserId.trim();
    }

    public String getFillRemark() {
        return fillRemark;
    }

    public void setFillRemark(String fillRemark) {
        this.fillRemark = fillRemark == null ? null : fillRemark.trim();
    }

    public Byte getOrdId() {
        return ordId;
    }

    public void setOrdId(Byte ordId) {
        this.ordId = ordId;
    }

    public Byte getXtype() {
        return xtype;
    }

    public void setXtype(Byte xtype) {
        this.xtype = xtype;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}