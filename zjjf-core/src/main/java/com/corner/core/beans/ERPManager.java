package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;
import com.corner.core.enums.ManagerCooperation;
import com.corner.core.enums.ManagerStatus;
import com.corner.core.utils.StringUtil;

public class ERPManager {
    private String id = StringUtil.getUUID();

    private String supplierId;

    private String managerName;

    private String managerCode;

    private Integer serialNum;

    private Date addTime;

    private Date updateTime;

    private String job;

    private String email;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String address;

    private String whTel;

    private String tel;

    private String fax;

    private String mobile;

    private String branderName;

    private String whAddress;

    private String whMobile;

    private String whBranderName;

    private String remark;

    private Byte cleaningDayStatus;

    private Integer cleaningDay;

    private String bankNum;

    private String bankUserName;

    private String bankName;

    private BigDecimal taxRate;

    private String taxNumber;

    private Byte cooperation;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode == null ? null : managerCode.trim();
    }

    public Integer getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(Integer serialNum) {
        this.serialNum = serialNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getWhTel() {
        return whTel;
    }

    public void setWhTel(String whTel) {
        this.whTel = whTel == null ? null : whTel.trim();
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getBranderName() {
        return branderName;
    }

    public void setBranderName(String branderName) {
        this.branderName = branderName == null ? null : branderName.trim();
    }

    public String getWhAddress() {
        return whAddress;
    }

    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress == null ? null : whAddress.trim();
    }

    public String getWhMobile() {
        return whMobile;
    }

    public void setWhMobile(String whMobile) {
        this.whMobile = whMobile == null ? null : whMobile.trim();
    }

    public String getWhBranderName() {
        return whBranderName;
    }

    public void setWhBranderName(String whBranderName) {
        this.whBranderName = whBranderName == null ? null : whBranderName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getCleaningDayStatus() {
        return cleaningDayStatus;
    }

    public void setCleaningDayStatus(Byte cleaningDayStatus) {
        this.cleaningDayStatus = cleaningDayStatus;
    }

    public Integer getCleaningDay() {
        return cleaningDay;
    }

    public void setCleaningDay(Integer cleaningDay) {
        this.cleaningDay = cleaningDay;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum == null ? null : bankNum.trim();
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName == null ? null : bankUserName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber == null ? null : taxNumber.trim();
    }

    public Byte getCooperation() {
        return cooperation;
    }
    public String getCooperationStr() {
        return getCooperation() == null ? "" : ManagerCooperation.getName(getCooperation());
    }
    public void setCooperation(Byte cooperation) {
        this.cooperation = cooperation;
    }

    public Byte getStatus() {
        return status;
    }
    public String getStatusStr(){
        return getStatus() == null ? "" : ManagerStatus.getName(getStatus());
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