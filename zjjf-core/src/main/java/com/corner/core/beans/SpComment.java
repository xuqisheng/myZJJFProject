package com.corner.core.beans;

import java.util.Date;

import com.corner.core.utils.StringUtil;

public class SpComment {
    private String id=StringUtil.getUUID();

    private String userId;

    private String userNm;

    private String userTel;

    private String userFace;

    private String userRemark;

    private Integer storeId;

    private String storeNm;

    private String orderId1;

    private String orderNo1;

    private String orderId2;

    private String orderNo2;

    private String spId;

    private String spNm;

    private String info;

    private Byte sendFen;

    private Byte qtyFen;

    private Byte attFen;

    private Byte unionFen;

    private Date addTime;

    private String csId;

    private String csNm;

    private String csDealInfo;

    private Date csDealTime;

    private Byte csDealstat;

    private Byte status;

    private Boolean isDelete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm == null ? null : userNm.trim();
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getUserFace() {
        return userFace;
    }

    public void setUserFace(String userFace) {
        this.userFace = userFace == null ? null : userFace.trim();
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark == null ? null : userRemark.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreNm() {
        return storeNm;
    }

    public void setStoreNm(String storeNm) {
        this.storeNm = storeNm == null ? null : storeNm.trim();
    }

    public String getOrderId1() {
        return orderId1;
    }

    public void setOrderId1(String orderId1) {
        this.orderId1 = orderId1 == null ? null : orderId1.trim();
    }

    public String getOrderNo1() {
        return orderNo1;
    }

    public void setOrderNo1(String orderNo1) {
        this.orderNo1 = orderNo1 == null ? null : orderNo1.trim();
    }

    public String getOrderId2() {
        return orderId2;
    }

    public void setOrderId2(String orderId2) {
        this.orderId2 = orderId2 == null ? null : orderId2.trim();
    }

    public String getOrderNo2() {
        return orderNo2;
    }

    public void setOrderNo2(String orderNo2) {
        this.orderNo2 = orderNo2 == null ? null : orderNo2.trim();
    }

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public String getSpNm() {
        return spNm;
    }

    public void setSpNm(String spNm) {
        this.spNm = spNm == null ? null : spNm.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Byte getSendFen() {
        return sendFen;
    }

    public void setSendFen(Byte sendFen) {
        this.sendFen = sendFen;
    }

    public Byte getQtyFen() {
        return qtyFen;
    }

    public void setQtyFen(Byte qtyFen) {
        this.qtyFen = qtyFen;
    }

    public Byte getAttFen() {
        return attFen;
    }

    public void setAttFen(Byte attFen) {
        this.attFen = attFen;
    }

    public Byte getUnionFen() {
        return unionFen;
    }

    public void setUnionFen(Byte unionFen) {
        this.unionFen = unionFen;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId == null ? null : csId.trim();
    }

    public String getCsNm() {
        return csNm;
    }

    public void setCsNm(String csNm) {
        this.csNm = csNm == null ? null : csNm.trim();
    }

    public String getCsDealInfo() {
        return csDealInfo;
    }

    public void setCsDealInfo(String csDealInfo) {
        this.csDealInfo = csDealInfo == null ? null : csDealInfo.trim();
    }

    public Date getCsDealTime() {
        return csDealTime;
    }

    public void setCsDealTime(Date csDealTime) {
        this.csDealTime = csDealTime;
    }

    public Byte getCsDealstat() {
        return csDealstat;
    }

    public void setCsDealstat(Byte csDealstat) {
        this.csDealstat = csDealstat;
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