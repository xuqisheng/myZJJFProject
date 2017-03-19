package com.corner.core.beans;

import java.math.BigDecimal;
import java.util.Date;

public class ScmsStore {
    private Integer id;

    private String spId;

    private Byte ordId;

    private String scode;

    private String name;

    private String remark;

    private String contact;

    private String tel;

    private String mobile;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String address;

    private String lng;

    private String lat;

    private String contactUser;

    private String contactPwd;

    private String shoperName;

    private String shoperTel;

    private String shoperQQ;

    private String bankAccount;

    private String bankName;

    private String bankCard;

    private String img;

    private Integer sales;

    private Date lastTime;

    private String lastIP;

    private Date addTime;

    private Date editTime;

    private Date sendTimeBegin;

    private Date sendTimeEnd;

    private BigDecimal sendess;

    private BigDecimal serviceFee;

    private Float rates;

    private Byte agreementPeriod;

    private Integer totalPeriod;

    private Date signTime;

    private String iosCid;

    private String androidCid;

    private Short totalComment;

    private BigDecimal avgComment;

    private Boolean isDrawC;

    private BigDecimal amount;

    private String qrcodeurl;

    private Integer spGroupId;

    private Boolean isFirstOrd;

    private Byte type;

    private Byte suType;

    private Byte status;

    private Boolean isDelete;

    private String explain;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public Byte getOrdId() {
        return ordId;
    }

    public void setOrdId(Byte ordId) {
        this.ordId = ordId;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode == null ? null : scode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser == null ? null : contactUser.trim();
    }

    public String getContactPwd() {
        return contactPwd;
    }

    public void setContactPwd(String contactPwd) {
        this.contactPwd = contactPwd == null ? null : contactPwd.trim();
    }

    public String getShoperName() {
        return shoperName;
    }

    public void setShoperName(String shoperName) {
        this.shoperName = shoperName == null ? null : shoperName.trim();
    }

    public String getShoperTel() {
        return shoperTel;
    }

    public void setShoperTel(String shoperTel) {
        this.shoperTel = shoperTel == null ? null : shoperTel.trim();
    }

    public String getShoperQQ() {
        return shoperQQ;
    }

    public void setShoperQQ(String shoperQQ) {
        this.shoperQQ = shoperQQ == null ? null : shoperQQ.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP == null ? null : lastIP.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Date getSendTimeBegin() {
        return sendTimeBegin;
    }

    public void setSendTimeBegin(Date sendTimeBegin) {
        this.sendTimeBegin = sendTimeBegin;
    }

    public Date getSendTimeEnd() {
        return sendTimeEnd;
    }

    public void setSendTimeEnd(Date sendTimeEnd) {
        this.sendTimeEnd = sendTimeEnd;
    }

    public BigDecimal getSendess() {
        return sendess;
    }

    public void setSendess(BigDecimal sendess) {
        this.sendess = sendess;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Float getRates() {
        return rates;
    }

    public void setRates(Float rates) {
        this.rates = rates;
    }

    public Byte getAgreementPeriod() {
        return agreementPeriod;
    }

    public void setAgreementPeriod(Byte agreementPeriod) {
        this.agreementPeriod = agreementPeriod;
    }

    public Integer getTotalPeriod() {
        return totalPeriod;
    }

    public void setTotalPeriod(Integer totalPeriod) {
        this.totalPeriod = totalPeriod;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getIosCid() {
        return iosCid;
    }

    public void setIosCid(String iosCid) {
        this.iosCid = iosCid == null ? null : iosCid.trim();
    }

    public String getAndroidCid() {
        return androidCid;
    }

    public void setAndroidCid(String androidCid) {
        this.androidCid = androidCid == null ? null : androidCid.trim();
    }

    public Short getTotalComment() {
        return totalComment;
    }

    public void setTotalComment(Short totalComment) {
        this.totalComment = totalComment;
    }

    public BigDecimal getAvgComment() {
        return avgComment;
    }

    public void setAvgComment(BigDecimal avgComment) {
        this.avgComment = avgComment;
    }

    public Boolean getIsDrawC() {
        return isDrawC;
    }

    public void setIsDrawC(Boolean isDrawC) {
        this.isDrawC = isDrawC;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getQrcodeurl() {
        return qrcodeurl;
    }

    public void setQrcodeurl(String qrcodeurl) {
        this.qrcodeurl = qrcodeurl == null ? null : qrcodeurl.trim();
    }

    public Integer getSpGroupId() {
        return spGroupId;
    }

    public void setSpGroupId(Integer spGroupId) {
        this.spGroupId = spGroupId;
    }

    public Boolean getIsFirstOrd() {
        return isFirstOrd;
    }

    public void setIsFirstOrd(Boolean isFirstOrd) {
        this.isFirstOrd = isFirstOrd;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getSuType() {
        return suType;
    }

    public void setSuType(Byte suType) {
        this.suType = suType;
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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain == null ? null : explain.trim();
    }
}